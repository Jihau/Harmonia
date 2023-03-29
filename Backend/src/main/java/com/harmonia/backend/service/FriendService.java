package com.harmonia.backend.service;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    public List<Friend> listFriendsByUserId(Long userId) {
        return friendRepository.listFriendsByUserId(userId);
    }

    public void addFriend(Long userId, Long friendId) {
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friendRepository.save(friend);
    }

    public void removeFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friend != null) {
            friendRepository.delete(friend);
        }
    }
}