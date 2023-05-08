package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.po.FriendRequest;
import com.harmonia.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing Friend entities.
 *
 * @author Harmonia team
 * @version 2.0
 */
@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     * GET endpoint for retrieving all Friends.
     * @param userId  the ID of the user whose friends to retrieve.
     * @return      a List of Friends.
     */
    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Friend>> getFriendsByUserId(@PathVariable Long userId) {
        List<Friend> friends = friendService.listFriendsByUserId(userId);
        return ResponseEntity.ok(friends);
    }

    /**
     * POST endpoint for adding a new Friend.
     * @param userId    the ID of the user to add a friend to.
     * @param friend    the Friend to add.
     * @return        a ResponseEntity with an HTTP status code.
     */
    @PostMapping("/userId/{userId}")
    public ResponseEntity<String> addFriend(@PathVariable Long userId, @RequestBody FriendRequest friend) {
        if (Objects.equals(userId, friend.getFriendId())) {
            return ResponseEntity.ok("Cannot add yourself as friend!");
        }
        friendService.addFriend(userId, friend.getFriendId());
        return ResponseEntity.ok("User with id " + friend.getFriendId() + " added as friend.");
    }


    /**
     * POST endpoint for removing a Friend.
     * @param userId    the ID of the user to remove a friend from.
     * @param friend    the Friend to remove.
     * @return      a ResponseEntity with an HTTP status code.
     */
    @PostMapping("/remove/userId/{userId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long userId, @RequestBody FriendRequest friend) {
        friendService.removeFriend(userId, friend.getFriendId());
        return ResponseEntity.ok("Friend with ID " + friend.getFriendId() + " removed!");
    }
}