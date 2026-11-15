package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.Auth.AuthUserResponseDTO;
import com.priyansh.cinebook.DTO.Auth.CsrfTokenResponseDTO;
import com.priyansh.cinebook.DTO.Auth.LoginRequestDTO;
import com.priyansh.cinebook.DTO.Auth.RegisterRequestDTO;
import com.priyansh.cinebook.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    @GetMapping("/csrf")
    public CsrfTokenResponseDTO csrfToken(CsrfToken csrfToken) {
        return new CsrfTokenResponseDTO(csrfToken.getToken());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthUserResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username, email, or phone number is already in use");
        }
    }

    @PostMapping("/login")
    public AuthUserResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse) {
        if (request.password().getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Password must be at most 72 UTF-8 bytes");
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    UsernamePasswordAuthenticationToken.unauthenticated(
                            request.username().trim(), request.password()));
        } catch (AuthenticationException exception) {
        System.err.println("AUTH DEBUG: Authentication failed: "
                + exception.getClass().getSimpleName());
        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Invalid username or password"
        );
    }

        AuthUserResponseDTO profile = authService.getProfile(authentication.getName());
        servletRequest.getSession(true);
        servletRequest.changeSessionId();
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        securityContextRepository.saveContext(context, servletRequest, servletResponse);

        return profile;
    }

    @GetMapping("/me")
    public AuthUserResponseDTO currentUser(Principal principal) {
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required");
        }
        return authService.getProfile(principal.getName());
    }
}