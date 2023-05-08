package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** A class representing a friend in a server.
 *  Implements Comparable to allow sorting by timestamp.
 *  Provides methods to create labels and pretty string representation.
 * @author Harmonia Team
 *  @version 2.0
 */
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
