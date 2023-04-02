package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendPo {
    int friendId;
    int userId;
    int userFriendId;

    @Override
    public String toString() {
        return "FriendPo{" + "friendId=" + friendId + ", userId=" + userId + '}';
    }
}
