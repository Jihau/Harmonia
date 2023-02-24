package com.harmonia.client;

import com.harmonia.po.UserPO;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.*;

public class UserClient {

    private RestTemplate restTemplate;

    public UserClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public UserPO[] listUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(USERS_LIST_URL, HttpMethod.GET, request, UserPO[].class).getBody();
    }

    public UserPO addUser(UserPO user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(USERS_ADD_URL, HttpMethod.POST, request, UserPO.class, urlParameters).getBody();
    }

    public ResponseEntity<Void> removeUser(int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        // Loading values in the URL
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(userId));
        return restTemplate.exchange(USERS_DELETE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }
}