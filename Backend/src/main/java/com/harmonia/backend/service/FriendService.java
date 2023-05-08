package com.harmonia.backend.service;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The FriendService class provides methods for adding, listing, and removing friends in the system.
 * This class is annotated with @Service, which tells Spring Boot that this class is a service class.
 *
 * @version 2.0
 * @author Harmonia team
 */

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepository;

    /**
     * Lists all friends of a user.
     * @param userId the id of the user
     * @return  a list of friends of the user
     */
    public List<Friend> listFriendsByUserId(Long userId) {
        return friendRepository.listFriendsByUserId(userId);
    }

    /**
     * Adds a friend to a user.
     * @param userId    the id of the user
     * @param friendId   the id of the friend
     */
    public void addFriend(Long userId, Long friendId) {
        Friend friend = new Friend();
        friend.setUserId(userId);
        friend.setFriendId(friendId);
        friendRepository.save(friend);
    }

    /**
     * Checks if a user is a friend of another user.
     * Removes a friend from a user.
     * @param userId    the id of the user
     * @param friendId   the id of the friend
     */
    public void removeFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findByUserIdAndFriendId(userId, friendId);
        if (friend != null) {
            friendRepository.delete(friend);
        }
    }
}