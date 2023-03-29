package com.harmonia.backend.po;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {
    private Long userId;
    private Long friendId;
    private String userName;
}
