
package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthPasswordDiagnosticTest {

    @Autowired
    private LoginDetailsRepo loginDetailsRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void verifyExistingUserPassword() {
        String username = "Priyansh019";

        // Enter the actual password locally.
        String expectedPassword = "abc";

        LoginDetails user = loginDetailsRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean matches = passwordEncoder.matches(
                expectedPassword,
                user.getPassword()
        );

        System.out.println("User found: true");
        System.out.println("BCrypt password matches: " + matches);

        assertTrue(matches, "Stored password does not match");
    }
}
