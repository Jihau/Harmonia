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

import static com.harmonia.constants.HarmoniaConstants.*;

/**
 * The ServerMemberClient class is responsible for handling HTTP requests related to server members.
 *
 * @author Harmonia Team
 * @version 1.0
 */
public class ServerMemberClient {

    /**
     * The RestTemplate object used to send HTTP requests.
     */
    protected RestTemplate restTemplate;

    /**
     * Constructs a new ServerMemberClient with a default RestTemplate and a MappingJackson2HttpMessageConverter.
     */
    public ServerMemberClient() {
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Retrieves an array of ServerMemberPO objects representing the servers that a given member is a part of.
     * @param memberId the ID of the member whose servers should be retrieved
     * @return an array of ServerMemberPO objects representing the servers that the member is a part of
     */
    public ServerMemberPO[] listServersByMemberId(int memberId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("memberId", String.valueOf(memberId));
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(SERVER_LIST_BY_MEMBER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }

    /**
     * Retrieves an array of ServerMemberPO objects representing the members of a given server.
     * @param serverId the ID of the server whose members should be retrieved
     * @return an array of ServerMemberPO objects representing the members of the server
     */
    public ServerMemberPO[] listMembersByServerId(Long serverId) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<?> request = new HttpEntity<>(headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("serverId", String.valueOf(serverId));
        ResponseEntity<ServerMemberPO[]> response = restTemplate.exchange(MEMBERS_BY_SERVER_ID_URL, HttpMethod.GET, request, ServerMemberPO[].class, urlParameters);
        return response.getBody();
    }

    /**
     * Adds a new member to a server.
     *
     * @param serverMemberPO the ServerMemberPO object containing the member and server ids.
     * @return a ResponseEntity with the newly added ServerMember object, or a HttpStatus.BAD_REQUEST if the addition failed.
     */
    public ResponseEntity<ServerMemberPO> addServerMember(ServerMemberPO serverMemberPO) {
        HttpHeaders headers = HarmoniaUtils.generateRequestHeaders();
        HttpEntity<ServerMemberPO> request = new HttpEntity<>(serverMemberPO, headers);
        return restTemplate.exchange(ADD_MEMBER_TO_SERVER_URL, HttpMethod.POST, request, ServerMemberPO.class);
    }
} 
