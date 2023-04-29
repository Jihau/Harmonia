package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.po.FriendRequest;
import com.harmonia.backend.po.FriendResponse;
import com.harmonia.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping("/userId/{userId}")
    public ResponseEntity<List<Friend>> getFriendsByUserId(@PathVariable Long userId) {
        List<Friend> friends = friendService.listFriendsByUserId(userId);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/userId/{userId}")
    public ResponseEntity<String> addFriend(@PathVariable Long userId, @RequestBody FriendRequest friend) {
        if (Objects.equals(userId, friend.getFriendId())) {
            return ResponseEntity.ok("Cannot add yourself as friend!");
        }
        friendService.addFriend(userId, friend.getFriendId());
        return ResponseEntity.ok("User with id " + friend.getFriendId() + " added as friend.");
    }

    @PostMapping("/remove/userId/{userId}")
    public ResponseEntity<String> removeFriend(@PathVariable Long userId, @RequestBody FriendRequest friend) {
        friendService.removeFriend(userId, friend.getFriendId());
        return ResponseEntity.ok("Friend with ID " + friend.getFriendId() + " removed!");
    }
}