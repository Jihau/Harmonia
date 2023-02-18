package com.harmonia.backend.controller;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PostMapping("register")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String,String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");
        User user =  userService.findByUsernameAndPassword(username,password);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);

    }



}
