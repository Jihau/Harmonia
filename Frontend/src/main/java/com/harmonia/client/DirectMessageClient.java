package com.harmonia.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.harmonia.model.Message;

import static com.harmonia.constants.HarmoniaConstants.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class DirectMessageClient {
    private RestTemplate restTemplate;

    public DirectMessageClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ResponseEntity<Message[]> getAllMessages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        
        return restTemplate.exchange(DM_GETALL_URL, HttpMethod.GET, request, Message[].class);
    }

    public ResponseEntity<Message> addMessage(Message newMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Message> request = new HttpEntity<>(newMessage, headers);
        Map<String, String> urlParameters = new HashMap<>();
        
        return restTemplate.exchange(DM_ADD_URL, HttpMethod.POST, request, Message.class, urlParameters);
    }

    public ResponseEntity<?> removeMessage(Message removeMe) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Message> request = new HttpEntity<>(removeMe, headers);
        Map<String, String> urlParameters = new HashMap<>();

        return restTemplate.exchange(DM_ADD_URL, HttpMethod.DELETE, request, Message.class, urlParameters);
    }
}
