package com.harmonia.client;

import com.harmonia.po.ServerPO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static com.harmonia.constants.HarmoniaConstants.*;

public class ServerClient {
    private RestTemplate restTemplate;

    public ServerClient() {
        restTemplate = new RestTemplate();
    }

    public ServerPO[] listAllServers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_ALL_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }

    public ServerPO[] listServersByName() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerPO[]> response = restTemplate.exchange(SERVER_LIST_BY_NAME_URL, HttpMethod.GET, request, ServerPO[].class);
        return response.getBody();
    }
}

