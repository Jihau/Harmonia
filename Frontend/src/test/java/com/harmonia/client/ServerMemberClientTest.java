package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.ServerMemberPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ServerMemberClientTest {
    @InjectMocks
    ServerMemberClient serverMemberClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader serverMemberReader= new ObjectMapper().readerFor(ServerMemberPO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listServersByMemberIdTest() throws JsonProcessingException {

        String response = "[{\"serverMemberId\":1,\"serverId\":2,\"member\":{\"userId\":5,\"username\":\"Jihau\",\"email\":\"jiiihau@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null,\"timestamp\":\"2023-03-01\"},\"memberId\":5,\"nickName\":\"Jihau\",\"joinDate\":\"2023-03-01\"}]";
        ServerMemberPO[] expectedServers = serverMemberReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerMemberPO[]>>any(), Mockito.anyMap()))
                .thenReturn(new ResponseEntity<>(expectedServers, HttpStatus.OK));
        ServerMemberPO[] actualServers =  serverMemberClient.listServersByMemberId(5);
        assertNotNull(actualServers);
        assertArrayEquals(expectedServers, actualServers);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerMemberPO[]>>any(), Mockito.anyMap());
    }
}
