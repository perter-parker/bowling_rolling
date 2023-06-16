package com.bowling.rolling.controller;

import com.bowling.rolling.model.users.domain.User;
import com.bowling.rolling.model.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public boolean login(@RequestBody User request) {
        String username = request.getUsername();
        String password = request.getPassword();
        return userService.login(username, password);
    }
}
