package com.harmonia.backend.service;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        if (user.getEmail().trim().length()==0){
            return null;
        }
        return userRepository.save(user);
    }
}
