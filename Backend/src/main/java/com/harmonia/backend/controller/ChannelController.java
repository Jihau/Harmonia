package com.harmonia.backend.controller;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing Channel entities.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    /**
     * GET endpoint for retrieving all Channel entities.
     *
     * @return an Iterable of Channel entities.
     */
    @GetMapping
    @CrossOrigin
    public Iterable<Channel> listChannels() {
        return channelService.listChannels();
    }

    /**
     * POST endpoint for creating a new Channel entity.
     *
     * @param channel the Channel entity to create.
     * @return the newly created Channel entity.
     */
    @PostMapping
    @CrossOrigin
    public Channel saveChannel(@RequestBody Channel channel){
        return channelService.addChannel(channel);
    }

    /**
     * DELETE endpoint for deleting a Channel entity by ID.
     *
     * @param channelId the ID of the Channel entity to delete.
     * @return a ResponseEntity with an HTTP status code.
     */
    @DeleteMapping("channelId/{channelId}")
    @CrossOrigin
    public ResponseEntity<Void> deleteChannel(@PathVariable Long channelId) {
        channelService.deleteChannel(channelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * PUT endpoint for updating a Channel entity's name by ID.
     *
     * @param channelId the ID of the Channel entity to update.
     * @param channel the updated Channel entity.
     * @return a ResponseEntity with a success message and HTTP status code.
     */
    @PutMapping("channelId/{channelId}")
    @CrossOrigin
    public ResponseEntity<String> editChannel(@PathVariable Long channelId, @RequestBody Channel channel){
        channelService.editChannel(channelId,channel.getChannelName());
        return ResponseEntity.ok("Channel name has been updated!");
    }
}
