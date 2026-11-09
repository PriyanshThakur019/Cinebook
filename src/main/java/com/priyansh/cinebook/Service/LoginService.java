package com.priyansh.cinebook.Service;

import com.priyansh.cinebook.DTO.UserDetailsDTO;
import com.priyansh.cinebook.Entity.LoginDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Repository.LoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginDetailsRepo loginDetailsRepo;

    public boolean authenticateLoginDetails(String username, String password) {
        return loginDetailsRepo.existsByUsernameAndPassword(username, password);
    }


    public List<LoginDetails> getAllLoginDetails() {
        return loginDetailsRepo.findAll();
    }
}
