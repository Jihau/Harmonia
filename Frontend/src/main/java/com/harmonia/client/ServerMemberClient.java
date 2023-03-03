package com.harmonia.client;

import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.*;

public class ServerMemberClient {
    private RestTemplate restTemplate;

    public ServerMemberClient() {
        restTemplate = new RestTemplate();
    }

    public ServerMemberPO[] listServersByMemberId(int memberId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("memberId", String.valueOf(memberId));
        
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(SERVER_LIST_BY_MEMBER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }

    public ServerMemberPO[] listMembersByServerId(Long serverId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("serverId", String.valueOf(serverId));

        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(MEMBERS_BY_SERVER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }
} 
