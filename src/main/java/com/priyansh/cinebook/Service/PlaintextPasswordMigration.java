package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "cinebook.auth.migrate-plaintext-passwords",
        havingValue = "true")
public class PlaintextPasswordMigration implements ApplicationRunner {

    private static final Pattern BCRYPT_HASH = Pattern.compile("^\\$2[aby]\\$\\d{2}\\$.{53}$");

    private final LoginDetailsRepo loginDetailsRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        List<LoginDetails> credentials = loginDetailsRepo.findAll();
        boolean hasUnsupportedPassword = credentials.stream()
            .filter(loginDetails -> loginDetails.getPassword() != null)
            .filter(loginDetails -> !BCRYPT_HASH.matcher(loginDetails.getPassword()).matches())
            .anyMatch(loginDetails -> loginDetails.getPassword()
                .getBytes(StandardCharsets.UTF_8).length > 72);
        if (hasUnsupportedPassword) {
            throw new IllegalStateException(
                "Password migration stopped: a legacy password exceeds BCrypt's 72-byte limit. "
                    + "Reset that account's password before enabling the migration.");
        }

        int migratedCount = 0;

        for (LoginDetails loginDetails : credentials) {
            String storedPassword = loginDetails.getPassword();
            if (storedPassword != null && !BCRYPT_HASH.matcher(storedPassword).matches()) {
                loginDetails.setPassword(passwordEncoder.encode(storedPassword));
                migratedCount++;
            }
        }

        if (migratedCount > 0) {
            loginDetailsRepo.saveAll(credentials);
        }
        log.info("Password hash migration completed; {} credentials migrated", migratedCount);
    }
}