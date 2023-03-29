package com.harmonia.backend.mappers;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.FriendRequest;

public class FriendMapper {

    public static Friend addFriendFromFriendRequest(FriendRequest friendRequest, User user){
        Friend friend = new Friend();
        friend.setFriendId(friend.getFriendId());
        return friend;
    }
}
