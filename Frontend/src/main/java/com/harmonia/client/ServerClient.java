package com.harmonia.client;

import com.harmonia.po.ServerPO;
import com.harmonia.utils.HarmoniaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.client.RestTemplate;

import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_ALL_URL;
import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_BY_NAME_URL;
import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_BY_ID_URL;

import java.util.HashMap;
import java.util.Map;

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

    public ServerPO getServerById(int id) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("serverId", String.valueOf(id));

        ResponseEntity<ServerPO> response = restTemplate.exchange(SERVER_LIST_BY_ID_URL, HttpMethod.GET, request, ServerPO.class, urlParameters);
        return response.getBody();
    }
}

