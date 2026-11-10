package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.DTO.UserDetailsDTO;
import com.priyansh.cinebook.Entity.BookingDetails;
import com.priyansh.cinebook.Entity.UserDetails;
import com.priyansh.cinebook.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserDetails getUserByloginDetails(@RequestParam String username, String password) {
        return userService.getUserDetailsByLoginCreds(username, password);
    }

    @PostMapping("/registerUser")
    public UserDetails registerUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        return userService.registerUser(userDetailsDTO);
    }

    @GetMapping("/getAllUsers")
    public List<UserDetails> getAllUserDetails() {
        return userService.getAllUserDetails();
    }

    @GetMapping("/getAllBookingsByUsername")
    public List<BookingDetails> getAllBookingsByUsername(@RequestParam String username) {
        return userService.getAllBookingsByUsername(username);
    }

    @GetMapping("/getAllFutureBookingsByUsername")
    public List<BookingDetails> getAllFutureBookingsByUsername(@RequestParam String username) {
        return userService.getAllFutureBookingsByUsername(username);
    }

    @GetMapping("/getAllPastBookingsByUsername")
    public List<BookingDetails> getAllPastBookingsByUsername(@RequestParam String username) {
        return userService.getAllPastBookingsByUsername(username);
    }
}
