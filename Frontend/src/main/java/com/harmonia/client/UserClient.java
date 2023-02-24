package com.harmonia.client;

import com.harmonia.po.UserPO;
import com.harmonia.po.ValidationPOJO;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;

import java.util.HashMap;
import java.util.Map;

import javax.imageio.spi.IIOServiceProvider;

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
        
        ResponseEntity<UserPO> response = request(user, HttpMethod.POST);

        return response.getBody();
    }

    public ResponseEntity<UserPO> removeUser(UserPO user) {
        
        ResponseEntity<UserPO> response = request(user, HttpMethod.DELETE);

        return response;
    }

    public ResponseEntity<UserPO> putUser(UserPO user) {

        ResponseEntity<UserPO> response = request(user,HttpMethod.PUT);

        return response;
    }

    public ResponseEntity<UserPO> editPassword(UserPO user) {

        //This lacks validation!!!

        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(user.getUserId()));

        ResponseEntity<UserPO> response = restTemplate.exchange(BASE_URL+"/"+user.getUserId()+"/password", HttpMethod.PUT, request,
                UserPO.class, params);
        return response;
    }

    public ResponseEntity<UserPO> editProfileIcon(UserPO user) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(user.getUserId()));

        ResponseEntity<UserPO> response = restTemplate.exchange(BASE_URL+"/"+user.getUserId()+"/password", HttpMethod.PUT, request,
                UserPO.class, params);
        return response;
    }

    private static ResponseEntity<UserPO> request(UserPO user, HttpMethod method ) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<UserPO> request = new HttpEntity<>(user, headers);
        
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(user.getUserId()));

        ResponseEntity<UserPO> response = restTemplate.exchange(BASE_URL, method, request,
                UserPO.class, params);
        return response;
    }

    public static HttpStatusCode validate(String username, String passwrd) {
    
    System.out.println("Validating for \nusername: " + username + "\npassword: " + passwrd);

        ValidationPOJO validateMe = new ValidationPOJO(username, passwrd);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        HttpEntity<ValidationPOJO> request = new HttpEntity<>(validateMe ,headers);

        ResponseEntity<UserPO> response = restTemplate.exchange(BASE_URL+"/login", HttpMethod.POST, request, UserPO.class);


        return response.getStatusCode();

    }
}