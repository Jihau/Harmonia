package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 *
 * This class represents a user entity in the application.
 * It contains information such as userId, username, email, password, profileIcon, bio, and timestamp.
 * The class is annotated with Lombok annotations: @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor,
 * which generate boilerplate code for getter, setter, no-args constructor, and all-args constructor.
 * The class has a field of type Date: timestamp, which represents the time when the user was created.
 * The class overrides the toString() method to return the username.
 *
 * @author Harmonia team
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
    int userId;
    String username;
    String email;
    String password;
    String profileIcon;
    String bio;
    Date timestamp;

    @Override
    public String toString() {
        return username;
    }
}
