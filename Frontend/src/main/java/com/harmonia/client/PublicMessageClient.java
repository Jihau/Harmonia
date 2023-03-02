package com.harmonia.client;

import static com.harmonia.constants.HarmoniaConstants.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.harmonia.po.PublicMessagePO;

public class PublicMessageClient {
    private RestTemplate restTemplate;

    public PublicMessageClient() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public PublicMessagePO[] getAllMessages() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<PublicMessagePO[]> response = restTemplate.exchange(PM_GETALL_URL, HttpMethod.GET, request, PublicMessagePO[].class);
        return response.getBody();
    }

    public PublicMessagePO sendPublicMessage(PublicMessagePO message) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(message ,headers);
        ResponseEntity<PublicMessagePO> response = restTemplate.exchange(PM_ADD_URL, HttpMethod.POST , request, PublicMessagePO.class);
        return response.getBody();
    }

    public ResponseEntity<?> removeMessage(PublicMessagePO removeMe) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(removeMe, headers);

        return restTemplate.exchange(PM_REMOVE_URL, HttpMethod.DELETE, request, String.class);
    }

    public ResponseEntity<?> editPublicMessage(PublicMessagePO editMe) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(editMe, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("pMessageId", String.valueOf(editMe.getPmessageId()));

        return restTemplate.exchange(PM_EDIT_URL, HttpMethod.PUT, request , String.class, urlParameters);
    }

}
