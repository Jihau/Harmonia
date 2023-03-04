package com.harmonia.client;

import com.harmonia.po.ServerMemberPO;
import com.harmonia.utils.HarmoniaUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.MEMBERS_BY_SERVER_ID_URL;
import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_BY_MEMBER_ID_URL;

public class ServerMemberClient {
    protected RestTemplate restTemplate;

    public ServerMemberClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    public ServerMemberPO[] listServersByMemberId(int memberId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("memberId", String.valueOf(memberId));
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(SERVER_LIST_BY_MEMBER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }

    public ServerMemberPO[] listMembersByServerId(Long serverId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("serverId", String.valueOf(serverId));
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(MEMBERS_BY_SERVER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }
} 
