package com.harmonia.backend.po;

import lombok.*;

/**
 * A class representing a request to create a new user.
 *
 * @version 2.0
 * @author Harmonia team
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    private Long userId;
    private Long friendId;
    private String userName;
}
