package com.harmonia.client;

import com.harmonia.po.ServerMemberPO;
import com.harmonia.po.ServerPO;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static com.harmonia.constants.HarmoniaConstants.SERVER_LIST_BY_MEMBER_ID_URL;

public class ServerMemberClient {
    private RestTemplate restTemplate;

    public ServerMemberClient() {
        restTemplate = new RestTemplate();
    }

    public ServerMemberPO[] listServersByMemberId() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> request = new HttpEntity<>(headers);
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(SERVER_LIST_BY_MEMBER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class);
        return response.getBody();
    }
}
