package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.Auth.AuthUserResponseDTO;
import com.priyansh.cinebook.DTO.Auth.RegisterRequestDTO;
import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import com.priyansh.cinebook.Repository.UserDetailsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    private LoginDetailsRepo loginDetailsRepo;
    private UserDetailsRepo userDetailsRepo;
    private PasswordEncoder passwordEncoder;
    private AuthService authService;

    @BeforeEach
    void setUp() {
        loginDetailsRepo = mock(LoginDetailsRepo.class);
        userDetailsRepo = mock(UserDetailsRepo.class);
        passwordEncoder = new BCryptPasswordEncoder(4);
        authService = new AuthService(loginDetailsRepo, userDetailsRepo, passwordEncoder);
    }

    @Test
    void registerStoresHashedPasswordAndNormalizedEmail() {
        when(loginDetailsRepo.existsByUsername("viewer7")).thenReturn(false);
        when(userDetailsRepo.existsByEmailIgnoreCase("viewer@example.com")).thenReturn(false);
        when(userDetailsRepo.existsByMobile("+14155550123")).thenReturn(false);
        when(loginDetailsRepo.saveAndFlush(any(LoginDetails.class))).thenAnswer(invocation -> {
            LoginDetails loginDetails = invocation.getArgument(0);
            loginDetails.setLoginDetailsId(4L);
            return loginDetails;
        });
        when(userDetailsRepo.saveAndFlush(any(UserDetails.class))).thenAnswer(invocation -> {
            UserDetails userDetails = invocation.getArgument(0);
            userDetails.setUserDetailsId(8L);
            return userDetails;
        });

        AuthUserResponseDTO response = authService.register(new RegisterRequestDTO(
                " viewer7 ", "Viewer Seven", " VIEWER@EXAMPLE.COM ", 28,
                "+14155550123", "saferPass123"));

        ArgumentCaptor<LoginDetails> loginCaptor = ArgumentCaptor.forClass(LoginDetails.class);
        verify(loginDetailsRepo).saveAndFlush(loginCaptor.capture());
        LoginDetails savedLogin = loginCaptor.getValue();
        assertEquals("viewer7", response.username());
        assertEquals("viewer@example.com", response.email());
        assertEquals(8L, response.userDetailsId());
        assertTrue(passwordEncoder.matches("saferPass123", savedLogin.getPassword()));
        assertNotEquals("saferPass123", savedLogin.getPassword());
    }

    @Test
    void duplicateUsernameReturnsConflictWithoutSaving() {
        when(loginDetailsRepo.existsByUsername("viewer7")).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> authService.register(new RegisterRequestDTO(
                        "viewer7", "Viewer Seven", "viewer@example.com", 28,
                        "+14155550123", "saferPass123")));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(loginDetailsRepo, never()).saveAndFlush(any(LoginDetails.class));
        verify(userDetailsRepo, never()).saveAndFlush(any(UserDetails.class));
    }

    @Test
    void passwordOverBcryptByteLimitIsRejected() {
        String password = "é".repeat(37);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> authService.register(new RegisterRequestDTO(
                        "viewer7", "Viewer Seven", "viewer@example.com", 28,
                        "+14155550123", password)));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(loginDetailsRepo, never()).saveAndFlush(any(LoginDetails.class));
    }
}