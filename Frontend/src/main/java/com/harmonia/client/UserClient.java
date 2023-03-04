package com.harmonia.client;

import com.harmonia.po.UserPO;
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

public class UserClient {
    protected RestTemplate restTemplate;

    public UserClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public UserPO[] listUsers() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(USERS_LIST_URL, HttpMethod.GET, request, UserPO[].class).getBody();
    }

    public UserPO addUser(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        return restTemplate.exchange(USERS_ADD_URL, HttpMethod.POST, request, UserPO.class, urlParameters).getBody();
    }

    public ResponseEntity<Void> removeUser(int userId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        // Loading values in the URL
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(userId));
        return restTemplate.exchange(USERS_DELETE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    public UserPO editPassword(int target, String newpassword) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        return restTemplate.exchange(BASE_URL + "/" + target + "/password", HttpMethod.PUT, request, UserPO.class).getBody();
    }

    public UserPO editIcon(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    public UserPO editBio(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    public UserPO editPassword(UserPO user) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(user.getUserId()));
        return restTemplate.exchange(USERS_EDIT_URL + "/password", HttpMethod.PUT, request, UserPO.class, urlParameters).getBody();
    }

    public ResponseEntity<UserPO> validate(String username, String passwrd) {
        UserPO validateMe = new UserPO();
        validateMe.setUsername(username);
        validateMe.setPassword(passwrd);
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(validateMe, headers);
        return restTemplate.exchange(USERS_LOGIN_URL, HttpMethod.POST, request, UserPO.class);
    }

    public UserPO getUserByID(int id) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<UserPO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("userId", String.valueOf(id));
        return restTemplate.exchange(USERS_EDIT_URL, HttpMethod.GET, request, UserPO.class, urlParameters).getBody();
    }
}