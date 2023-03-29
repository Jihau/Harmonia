package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Friend;
import com.harmonia.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/userId/{userId}/add/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/userId/{userId}/remove/{friendId}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long userId, @PathVariable Long friendId) {
        friendService.removeFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }
}