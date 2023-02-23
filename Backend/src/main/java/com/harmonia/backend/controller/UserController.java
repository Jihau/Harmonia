package com.harmonia.backend.controller;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @CrossOrigin
    public Iterable<User> listUsers(@RequestParam(name = "username", required = false) String userName) {
        if (userName != null) {
            return userService.listUsersByUserName(userName);
        }
        return userService.listUsers();
    }

    @GetMapping("username/{username}")
    @CrossOrigin
    public Iterable<User> listUsersByUserName(@PathVariable("username") String userName) {
        return userService.listUsersByUserName(userName);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    @PostMapping("login")
    public ResponseEntity<User> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        User user = userService.findByUsernameAndPassword(username, password);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
//        if (password.equals(user.getPassword())) {
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
    }

    @DeleteMapping
    @CrossOrigin
    public ResponseEntity<String> deleteUser(@RequestBody User user){
        System.out.println("Controller: User with id : " + user.getUserId() + "is deleted.");
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<User> editUsersByUserId(@PathVariable Long userId,@RequestBody User user) {
        return new ResponseEntity<>(userService.editUser(userId, user), HttpStatus.OK);
    }
}