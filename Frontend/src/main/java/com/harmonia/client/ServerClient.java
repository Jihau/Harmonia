package com.harmonia.client;

import com.harmonia.po.ServerPO;
import com.harmonia.utils.HarmoniaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_ALL_URL;
import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_BY_NAME_URL;

public class ServerClient {
    protected RestTemplate restTemplate;

    public ServerClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ServerPO[] listAllServers() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_ALL_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }

    public ServerPO[] listServersByName() {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_BY_NAME_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }
}

