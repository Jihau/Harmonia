package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.FriendRequest;

/**
 * A mapper class for creating Friend objects from FriendRequest objects.
 *
 * @version 2.0
 * @author Harmonia team
 */
public class FriendMapper {

    /**
     * Creates a Friend object from a FriendRequest object.
     * @param friendRequest     The FriendRequest object containing data about the friend to create.
     * @param user            The user to which the friend belongs.
     * @return              A Friend object with the friend's details loaded from the input parameter.
     */
    public static Friend addFriendFromFriendRequest(FriendRequest friendRequest, User user){
        Friend friend = new Friend();
        friend.setFriendId(friend.getFriendId());
        return friend;
    }
}
