package com.priyansh.cinebook.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Value("${spring.datasource.username}")
    private String dbUsername;
    @Value("${spring.datasource.password}")
    private String password;

    public boolean checkAdminCreds(String username, String password) {
        return this.dbUsername.equals(username)
                && this.password.equals(password);
    }
}