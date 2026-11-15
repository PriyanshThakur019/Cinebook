package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.Auth.AuthUserResponseDTO;
import com.priyansh.cinebook.DTO.Auth.RegisterRequestDTO;
import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import com.priyansh.cinebook.Repository.UserDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final LoginDetailsRepo loginDetailsRepo;
    private final UserDetailsRepo userDetailsRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthUserResponseDTO register(RegisterRequestDTO request) {
        String username = request.username().trim();
        String email = request.email().trim().toLowerCase(Locale.ROOT);
        String mobile = request.number().trim();

        if (request.password().getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Password must be at most 72 UTF-8 bytes");
        }

        if (loginDetailsRepo.existsByUsername(username)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username is already in use");
        }
        if (userDetailsRepo.existsByEmailIgnoreCase(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already in use");
        }
        if (userDetailsRepo.existsByMobile(mobile)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number is already in use");
        }

        try {
            LoginDetails loginDetails = loginDetailsRepo.saveAndFlush(LoginDetails.builder()
                .username(username)
                .password(passwordEncoder.encode(request.password()))
                .build());

            UserDetails userDetails = userDetailsRepo.saveAndFlush(UserDetails.builder()
                .loginDetails(loginDetails)
                .name(request.name().trim())
                .email(email)
                .mobile(mobile)
                .age(request.age())
                .build());

            return toResponse(userDetails);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Username, email, or phone number is already in use");
        }
    }

    @Transactional(readOnly = true)
    public AuthUserResponseDTO getProfile(String username) {
        UserDetails userDetails = userDetailsRepo.findByLoginDetails_Username(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User profile was not found"));

        return toResponse(userDetails);
    }

    private AuthUserResponseDTO toResponse(UserDetails userDetails) {
        return new AuthUserResponseDTO(
                userDetails.getUserDetailsId(),
                userDetails.getLoginDetails().getUsername(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getAge(),
                userDetails.getMobile());
    }
}