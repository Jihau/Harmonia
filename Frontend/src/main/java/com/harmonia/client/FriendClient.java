package com.harmonia.client;

import com.harmonia.po.FriendPo;
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

public class FriendClient {
    protected RestTemplate restTemplate;

    public FriendClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public FriendPo[] listFriends() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(FRIEND_LIST_BY_USERID_URL, HttpMethod.GET, request, FriendPo[].class).getBody();
    }

    public FriendPo addFriend(FriendPo friendPo) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<FriendPo> request = new HttpEntity<>(friendPo, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(FRIEND_ADD_URL, HttpMethod.POST, request, FriendPo.class, urlParameters).getBody();
    }

    public ResponseEntity<Void> removeFriend(int userId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(userId));
        return restTemplate.exchange(FRIEND_REMOVE_URL, HttpMethod.POST, request, Void.class, urlParameters);

    }
}
