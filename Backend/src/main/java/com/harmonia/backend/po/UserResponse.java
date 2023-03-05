package com.harmonia.backend.po;

import com.harmonia.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A class representing a response to a user request.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    /**
     * The ID of the user.
     */
    Long userId;

    /**
     * The username of the user.
     */
    String username;

    /**
     * The email of the user.
     */
    String email;

    /**
     * The profile icon of the user.
     */
    String profileIcon;

    /**
     * The bio of the user.
     */
    String bio;

    /**
     * Constructs a {@link UserResponse} object from a User object.
     *
     * @param user the User object to use
     */
    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profileIcon = user.getProfileIcon();
        this.bio = user.getBio();
    }
}
