package com.priyansh.cinebook.Service;

import ch.qos.logback.core.util.StringUtil;
import com.priyansh.cinebook.DTO.UserDetailsDTO;
import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import com.priyansh.cinebook.Repository.UserDetailsRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserDetailsRepo userDetailsRepo;
    @Autowired
    private LoginDetailsRepo loginDetailsRepo;


    public UserDetails getUserDetailsByLoginCreds(String username, String password) {
        UserDetails userDetails = null;

        if (loginService.authenticateLoginDetails(username, password)) {
            userDetails = userDetailsRepo.findByLoginDetails_Username(username).orElse(null);
        }

        return userDetails;
    }

    @Transactional
    public UserDetails registerUser(UserDetailsDTO userDetailsDTO) {
        if (StringUtil.isNullOrEmpty(userDetailsDTO.getPassword())) {
            log.error("No or null password given which is not allowed during time of registration");
            return null;
        }

        if (userDetailsRepo.existsByLoginDetails_Username(userDetailsDTO.getUsername())) {
            log.error("username already exist");
            return null;
        }

        if (userDetailsRepo.existsByMobile(String.valueOf(userDetailsDTO.getNumber()))) {
            log.error("mobile already exist already exist");
            return null;
        }
        LoginDetails loginDetails = LoginDetails.builder()
                .username(userDetailsDTO.getUsername())
                .password(userDetailsDTO.getPassword())
                .build();

        loginDetailsRepo.save(loginDetails);

        UserDetails userDetails = UserDetails.builder()
                .age(userDetailsDTO.getAge())
                .name(userDetailsDTO.getName())
                .mobile(String.valueOf(userDetailsDTO.getNumber()))
                .loginDetails(loginDetails)
                .build();

        userDetailsRepo.save(userDetails);
        return userDetails;
    }

    public List<UserDetails> getAllUserDetails() {
        return userDetailsRepo.findAll();
    }
}
