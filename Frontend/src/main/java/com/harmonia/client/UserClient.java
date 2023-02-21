package com.harmonia.client;

import com.harmonia.po.UserPO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class UserClient {
    private static final String BASE_URL = "http://localhost:8080/user";
    private final RestTemplate restTemplate;

    public UserClient() {
        restTemplate = new RestTemplate();
    }

    public String listUsers() {
        //return restTemplate.getForObject(BASE_URL, UserPO[].class);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<?>httpEntity= new HttpEntity<>(headers);
        return restTemplate.exchange(BASE_URL, HttpMethod.GET, httpEntity, String.class).getBody();
    }

    public UserPO addUser(UserPO user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(user.getUserId()));

        ResponseEntity<UserPO> response = restTemplate.exchange(BASE_URL, HttpMethod.POST, request,
                UserPO.class, params);
        return response.getBody();
    }
}