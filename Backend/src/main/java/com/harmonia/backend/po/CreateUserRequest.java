package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * A class representing a request to create a new user.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    /**
     * The desired username for the new user.
     */
    String username;

    /**
     * The email address of the new user.
     */
    String email;

    /**
     * The desired password for the new user.
     */
    String password;

    /**
     * The URL of the profile icon for the new user.
     */
    String profileIcon;

}
