package com.harmonia.backend.controller;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @CrossOrigin
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
