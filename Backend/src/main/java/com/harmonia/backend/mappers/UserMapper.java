package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.CreateUserRequest;

public class UserMapper {
    public static User createUserFromCreateUserRequest(CreateUserRequest userRequest){
        User createdUser = new User();
        createdUser.setEmail(userRequest.getEmail());
        createdUser.setUsername(userRequest.getUsername());
        createdUser.setPassword(userRequest.getPassword());
        createdUser.setProfileIcon(userRequest.getProfileIcon());
        return createdUser;
    }
}
