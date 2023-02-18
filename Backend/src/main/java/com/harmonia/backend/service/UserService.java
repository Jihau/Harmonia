package com.harmonia.backend.service;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //private static final String API_URL = "http://localhost:8080/api/users";

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    public Iterable<User> listUsersByUserName(String userName) {
        return userRepository.listUsersByUserName("%" + userName + "%");
    }

    public User addUser(User user) {
        if (user.getEmail().trim().length() == 0) {
            return null;
        }
        // logic check the username before adding it
        return userRepository.save(user);
    }


    public User createUser(User user) {
        //check if the username is already taken!
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already taken");
        }
        //hashing and salting the password
        //String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        //Save the user
        //user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User findByUsernameAndPassword(String username, String password){
        //Find the user by username
        User user = userRepository.findByUsername(username);
        System.out.println("response password " + user.getPassword());
        System.out.println("request password "+password);
        System.out.println(user);

        //Check if the user exists and if the Password is correct
        if(user != null && (password.equals(user.getPassword()))){

            System.out.println("Logged in!");
            return user;

        }else {
            System.out.println("Failed");
            return null;
        }
    }


}