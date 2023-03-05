package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * A class representing a request to change a user's password.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class ChangePasswordRequest {
    String password;
}
