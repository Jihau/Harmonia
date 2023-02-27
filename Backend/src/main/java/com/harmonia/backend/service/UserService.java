package com.harmonia.backend.service;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<UserResponse> listUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false).map(UserResponse::new).collect(Collectors.toList());
    }

    public Iterable<UserResponse> listUsersByUserName(String userName) {
        return userRepository.listUsersByUserName("%" + userName + "%").stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public User addUser(User user) {
        if (user.getEmail().trim().length() == 0) {
            return null;
        }
        // logic check the username before adding it
        return userRepository.save(user);
    }


    public UserResponse createUser(User user) {
        //check if the username is already taken!
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }
        if (user.getUsername().trim().length() != 0 && user.getEmail().trim().length() != 0) {
            //hashing and salting the password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword);
            //Save the user
            return new UserResponse(userRepository.save(user));

        }else {
            throw new RuntimeException("All fields must be filled");
        }

    }

    public UserResponse findByUsernameAndPassword(String username, String password) {
        //Find the user by username
        User user = userRepository.findByUsername(username);
        System.out.println("request password " + password);
        System.out.println(user);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            System.out.println("Logged in!");
            return new UserResponse(user);
        } else {
            System.out.println("Failed");
            return null;
        }
    }

    public UserResponse findByUsername(String username) {
        //Find the user by username
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        System.out.println("response password " + user.getPassword());
        System.out.println(user);
        return new UserResponse(user);
    }

    public UserResponse findUserById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return new UserResponse(user.get());
        }
        throw new RuntimeException("User not found");
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
        System.out.println("User with id " + userId + " has been deleted!");
    }

    public UserResponse editUser(Long userId, User user) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            existingUser.get().setProfileIcon(user.getProfileIcon());
            System.out.println("User's with id " + userId + " username has been updated to " + existingUser.get() + " successfully");
            return new UserResponse(userRepository.save(existingUser.get()));
        }
        return null;
    }

    public UserResponse changePassword(Long userId, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            return new UserResponse(userRepository.save(user));
        }
        return null;
    }
}