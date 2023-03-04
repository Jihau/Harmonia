package com.harmonia.client;

import com.harmonia.po.PublicMessagePO;
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

public class PublicMessageClient {
    protected RestTemplate restTemplate;

    public PublicMessageClient() {
        this.restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public PublicMessagePO[] getAllMessages() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<PublicMessagePO[]> response = restTemplate.exchange(PM_GETALL_URL, HttpMethod.GET, request, PublicMessagePO[].class);
        return response.getBody();
    }

    public PublicMessagePO sendPublicMessage(PublicMessagePO message) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(message, headers);
        ResponseEntity<PublicMessagePO> response = restTemplate.exchange(PM_ADD_URL, HttpMethod.POST, request, PublicMessagePO.class);
        return response.getBody();
    }

    public ResponseEntity<Void> removeMessage(Long pMessageId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("pMessageId", String.valueOf(pMessageId));
        return restTemplate.exchange(PM_REMOVE_URL, HttpMethod.DELETE, request, Void.class, urlParameters);
    }

    public PublicMessagePO editPublicMessage(PublicMessagePO editMe) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<PublicMessagePO> request = new HttpEntity<>(editMe, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("pMessageId", String.valueOf(editMe.getPmessageId()));

        return restTemplate.exchange(PM_EDIT_URL, HttpMethod.PUT, request, PublicMessagePO.class, urlParameters).getBody();
    }

}
