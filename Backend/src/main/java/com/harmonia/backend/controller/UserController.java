package com.harmonia.backend.controller;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.ChangePasswordRequest;
import com.harmonia.backend.po.CreateUserRequest;
import com.harmonia.backend.po.CredentialsPO;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Rest controller for handling user-related HTTP requests.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * The service for user-related business logic
     */
    @Autowired
    private UserService userService;

    /**
     * Constructor for UserController class
     *
     * @param userService the user service to be used
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get a list of all users or a list of users by username if a username is provided
     *
     * @return an iterable list of UserResponse objects
     */
    @GetMapping
    @CrossOrigin
    public Iterable<UserResponse> listUsers() {
        return userService.listUsers();
    }

    /**
     * Get a list of users by username
     *
     * @param userName the username to search for
     * @return an iterable list of UserResponse objects
     */
    @GetMapping("username/{username}")
    @CrossOrigin
    public Iterable<UserResponse> listUsersByUserName(@PathVariable("username") String userName) {
        return userService.listUsersByUserName(userName);
    }

    /**
     * Get a user by their user ID
     *
     * @param userId the ID of the user to retrieve
     * @return a UserResponse object
     */
    @GetMapping("userId/{userId}")
    @CrossOrigin
    public UserResponse findUserById(@PathVariable Long userId) {
        return userService.findUserById(userId);
    }

    /**
     * Create a new user
     *
     * @param user the CreateUserRequest object containing the user data
     * @return a UserResponse object
     */
    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest user) {
        return userService.createUser(user);
    }


    /**
     * Authenticate a user by their username and password
     *
     * @param credentials a Map containing the username and password of the user
     * @return a ResponseEntity with a UserResponse object if the user is authenticated, or a not found response if not
     */
    @PostMapping("login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody CredentialsPO credentials) {

        UserResponse user = userService.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * Delete a user by their user ID
     *
     * @param userId the ID of the user to delete
     * @return a ResponseEntity with a status of OK
     */
    @DeleteMapping("userId/{userId}")
    @CrossOrigin
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        System.out.println("Controller: User with id : " + userId + "is deleted.");
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Edit a user by their user ID
     *
     * @param userId the ID of the user to edit
     * @param user   the User object containing the updated user data
     * @return a ResponseEntity with a UserResponse object and a status of OK
     */
    @PutMapping("userId/{userId}")
    @CrossOrigin
    public ResponseEntity<UserResponse> editUsersByUserId(@PathVariable Long userId, @RequestBody User user) {
        return new ResponseEntity<>(userService.editUser(userId, user), HttpStatus.OK);
    }

    /**
     * Changes the password of the user identified by userId
     *
     * @param userId                the ID of the user to change the password for
     * @param changePasswordRequest the new password to set for the user
     * @return a ResponseEntity with a UserResponse and HTTP status OK if the password was changed successfully,
     * or HTTP status NOT_FOUND if the user is not found
     */
    @PutMapping("userId/{userId}/password")
    public ResponseEntity<UserResponse> changePassword(@PathVariable Long userId, @RequestBody ChangePasswordRequest changePasswordRequest) {
        UserResponse user = userService.changePassword(userId, changePasswordRequest.getPassword());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}