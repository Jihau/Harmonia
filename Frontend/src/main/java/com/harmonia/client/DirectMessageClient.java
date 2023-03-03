package com.harmonia.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.util.RequestPayload;
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
        return response.getBody();
    }

    public ResponseEntity<MessagePO[]> getMessagesByRecipientID(int userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("recipientId", String.valueOf(userId));
        
        return restTemplate.exchange(DM_GET_R_ID, HttpMethod.GET, request, MessagePO[].class, urlParameters);
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

            System.out.println(DM_ADD_URL);

        return restTemplate.exchange(DM_ADD_URL, HttpMethod.DELETE, request, String.class, urlParameters);
    }

    public ResponseEntity<?> editMessage(MessagePO message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<MessagePO>(message, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("DmessageId", String.valueOf(message.getDmessageId()));

        return restTemplate.exchange(DM_EDIT_URL, HttpMethod.PUT, request, String.class, urlParameters);
    }

    public ResponseEntity<MessagePO[]> getMessagesByAuthorId(long l) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("authorId", String.valueOf(l));
        
        return restTemplate.exchange(DM_GET_A_ID, HttpMethod.GET, request, MessagePO[].class, urlParameters);
    }
}
