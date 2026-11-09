package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.UserDetailsDTO;
import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/authernticate")
    public boolean authenticateCreds(@RequestParam String username, String password) {
        log.info("username is {} and password is {}",username, password);
        return loginService.authenticateLoginDetails(username, password);
    }

    @GetMapping("/getAllLoginDetails")
    public List<LoginDetails> getAllLoginDetails() {
        return loginService.getAllLoginDetails();
    }
}
