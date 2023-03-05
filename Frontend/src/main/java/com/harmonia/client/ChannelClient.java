package com.harmonia.client;

import com.harmonia.po.ChannelPO;
import com.harmonia.utils.HarmoniaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.*;

/**
 * A client for interacting with channels in the Harmonia system.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class ChannelClient {

    /**
     * The REST template used to make HTTP requests.
     */
    protected RestTemplate restTemplate;

    /**
     * Constructs a new `ChannelClient` with a default `RestTemplate`.
     */
    public ChannelClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Retrieves an array of all channels.
     *
     * @return an array of `ChannelPO` objects representing all channels
     */
    public ChannelPO[] listAllChannels() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ChannelPO[]> response = restTemplate.exchange(CHANNEL_LIST_ALL_URL, HttpMethod.GET, request, ChannelPO[].class);
        return response.getBody();
    }

    /**
     * Adds a new channel.
     *
     * @param channel the `ChannelPO` object representing the new channel
     * @return the `ChannelPO` object representing the newly added channel
     */
    public ChannelPO addChannel(ChannelPO channel) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<ChannelPO> request = new HttpEntity<>(channel, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(CHANNEL_ADD_URL, HttpMethod.POST, request, ChannelPO.class, urlParameters).getBody();
    }

    /**
     * Removes a channel by ID.
     *
     * @param channelId the ID of the channel to remove
     * @return a `ResponseEntity` representing the result of the request
     */
    public ResponseEntity<Void> removeChannel(int channelId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("channelId", String.valueOf(channelId));
        return restTemplate.exchange(CHANNEL_DELETE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    /**
     * Edits an existing channel.
     *
     * @param channel the `ChannelPO` object representing the edited channel
     * @return the `ChannelPO` object representing the edited channel
     */
    public ChannelPO editChannel(ChannelPO channel) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<ChannelPO> request = new HttpEntity<>(channel, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("channelId", String.valueOf(channel.getChannelId()));
        return restTemplate.exchange(CHANNEL_EDIT_URL, HttpMethod.PUT, request, ChannelPO.class, urlParameters).getBody();
    }
}
