package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @GetMapping
    @CrossOrigin
    public Iterable<Channel> listChannels() {
        return channelService.listChannels();
    }
    @PostMapping
    @CrossOrigin
    public Channel saveChannel(@RequestBody Channel channel){
        return channelService.addChannel(channel);
    }

    @DeleteMapping
    @CrossOrigin
    public ResponseEntity<Void> deleteChannel(@RequestBody Channel channel) {
        channelService.deleteChannel(channel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}