package com.harmonia.backend.controller;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.ChangePasswordRequest;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public Iterable<UserResponse> listUsers(@RequestParam(name = "username", required = false) String userName) {
        if (userName != null) {
            return userService.listUsersByUserName(userName);
        }
        return userService.listUsers();
    }

    @GetMapping("username/{username}")
    @CrossOrigin
    public Iterable<UserResponse> listUsersByUserName(@PathVariable("username") String userName) {
        return userService.listUsersByUserName(userName);
    }

    @GetMapping("/{userId}")
    @CrossOrigin
    public UserResponse findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody User user) {
        return userService.createUser(user);
    }


    @PostMapping("login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        UserResponse user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        System.out.println("Controller: User with id : " + userId + "is deleted.");
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @CrossOrigin
    public ResponseEntity<UserResponse> editUsersByUserId(@PathVariable Long userId,@RequestBody User user) {
        return new ResponseEntity<>(userService.editUser(userId, user), HttpStatus.OK);
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<UserResponse> changePassword(@PathVariable Long userId, @RequestBody ChangePasswordRequest changePasswordRequest) {
        UserResponse user = userService.changePassword(userId, changePasswordRequest.getPassword());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}