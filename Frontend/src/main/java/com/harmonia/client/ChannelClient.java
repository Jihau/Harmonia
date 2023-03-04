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


public class ChannelClient {
    protected RestTemplate restTemplate;

    public ChannelClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ChannelPO[] listAllChannels() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ChannelPO[]> response = restTemplate.exchange(CHANNEL_LIST_ALL_URL, HttpMethod.GET, request, ChannelPO[].class);
        return response.getBody();
    }

    public ChannelPO addChannel(ChannelPO channel) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<ChannelPO> request = new HttpEntity<>(channel, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(CHANNEL_ADD_URL, HttpMethod.POST, request, ChannelPO.class, urlParameters).getBody();
    }

    public ResponseEntity<Void> removeChannel(int channelId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("channelId", String.valueOf(channelId));
        return restTemplate.exchange(CHANNEL_DELETE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    public ChannelPO editChannel(ChannelPO channel) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<ChannelPO> request = new HttpEntity<>(channel, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("channelId", String.valueOf(channel.getChannelId()));
        return restTemplate.exchange(CHANNEL_EDIT_URL, HttpMethod.PUT, request, ChannelPO.class, urlParameters).getBody();
    }
}
