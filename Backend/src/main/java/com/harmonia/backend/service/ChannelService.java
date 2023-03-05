package com.harmonia.backend.service;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The ChannelService class provides methods for adding, listing, deleting, and editing channels in the system.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class ChannelService {

    /**
     * Autowired ChannelRepository for accessing channel data from the database.
     */
    @Autowired
    private ChannelRepository channelRepository;

    /**
     * Add a channel to the database.
     * @param channel The channel to be added
     * @return The added channel
     */
    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    /**
     * List all channels in the database.
     * @return The list of channels
     */
    public Iterable<Channel> listChannels() {
        return channelRepository.findAll();
    }

    /**
     * Delete a channel from the database by its ID.
     * @param channelId The ID of the channel to be deleted
     */
    public void deleteChannel(Long channelId) {
        channelRepository.deleteById(channelId);
    }

    /**
     * Edit the name of a channel in the database by its ID.
     * @param channelId The ID of the channel to be edited
     * @param channelName The new name for the channel
     */
    public void editChannel(Long channelId, String channelName) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            channel.get().setChannelName(channelName);
            channelRepository.save(channel.get());
            System.out.println("Channel with id " + channelId + " name has been updated to " + channelName + " successfully.");
        } else {
            throw new IllegalArgumentException(("Can't change the name!"));
        }
    }
}
