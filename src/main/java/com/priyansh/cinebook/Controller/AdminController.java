package com.priyansh.cinebook.Controller;

import com.priyansh.cinebook.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/userIsAdmin")
    private boolean userIsAdmin(@RequestParam String username, @RequestParam String password) {
        return adminService.checkAdminCreds(username, password);
    }
}
