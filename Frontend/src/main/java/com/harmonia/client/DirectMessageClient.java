package com.harmonia.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import com.harmonia.po.MessagePO;

import static com.harmonia.constants.HarmoniaConstants.*;

import java.util.HashMap;
import java.util.Map;

public class DirectMessageClient {
    private RestTemplate restTemplate;

    public DirectMessageClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public MessagePO[] getAllMessages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<MessagePO[]> response = restTemplate.exchange(DM_GETALL_URL, HttpMethod.GET, request, MessagePO[].class);
        System.out.println(DM_GETALL_URL);
        System.out.println(response.getBody());
        return response.getBody();
    }

    public ResponseEntity<MessagePO[]> getMessagesByRecepientUID(int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("recipientId", String.valueOf(userId));
        
        return restTemplate.exchange(DM_ADD_URL, HttpMethod.POST, request, MessagePO[].class, urlParameters);
    }

    public ResponseEntity<MessagePO> addMessage(MessagePO newMessage) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(newMessage, headers);
        Map<String, String> urlParameters = new HashMap<>();
        
        return restTemplate.exchange(DM_ADD_URL, HttpMethod.POST, request, MessagePO.class, urlParameters);
    }

    public ResponseEntity<?> removeMessage(MessagePO removeMe) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(removeMe, headers);
        Map<String, String> urlParameters = new HashMap<>();

        return restTemplate.exchange(DM_ADD_URL, HttpMethod.DELETE, request, MessagePO.class, urlParameters);
    }

    public ResponseEntity<?> editMessage(MessagePO message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<MessagePO>(message, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("DmessageId", String.valueOf(message.getMessageId()));

        return restTemplate.exchange(DM_EDIT_URL, HttpMethod.PUT, request, MessagePO.class, urlParameters);
    }
}
