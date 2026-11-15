package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinebookUserDetailsService implements UserDetailsService {

    private final LoginDetailsRepo loginDetailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("AUTH DEBUG: Looking up username: [" + username + "]");

        LoginDetails loginDetails = loginDetailsRepo.findByUsername(username.trim())
                .orElseThrow(() -> {
                    System.out.println("AUTH DEBUG: User not found");
                    return new UsernameNotFoundException("Invalid credentials");
                });

        System.out.println("AUTH DEBUG: User found");
        System.out.println("AUTH DEBUG: Stored password starts with BCrypt: "
                + loginDetails.getPassword().startsWith("$2"));

        return User.withUsername(loginDetails.getUsername())
                .password(loginDetails.getPassword())
                .authorities("ROLE_CUSTOMER")
                .build();
    }
}