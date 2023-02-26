package com.harmonia.backend.service;

import com.harmonia.backend.domain.Channel;
import com.harmonia.backend.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public Channel addChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    public Iterable<Channel> listChannels() {
        return channelRepository.findAll();
    }

    public void deleteChannel(Channel channel){
        channelRepository.deleteById(channel.getChannelId());
    }

    public void editChannel(Long channelId, String channelName){
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
