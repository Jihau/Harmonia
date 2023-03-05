package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.CreateUserRequest;

/**
 * A mapper class for creating User objects from CreateUserRequest objects.
 */
public class UserMapper {
    /**
     * Creates a User object from a CreateUserRequest object.
     *
     * @param userRequest The CreateUserRequest object containing data about the user to create.
     * @return A User object with the user's details loaded from the input parameter.
     */
    public static User createUserFromCreateUserRequest(CreateUserRequest userRequest) {
        User createdUser = new User();
        createdUser.setEmail(userRequest.getEmail());
        createdUser.setUsername(userRequest.getUsername());
        createdUser.setPassword(userRequest.getPassword());
        createdUser.setProfileIcon(userRequest.getProfileIcon());
        return createdUser;
    }
}
