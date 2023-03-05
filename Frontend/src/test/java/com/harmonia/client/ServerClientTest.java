package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.MessagePO;
import com.harmonia.po.ServerPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ServerClientTest {
    @InjectMocks
    ServerClient serverClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader serverReader= new ObjectMapper().readerFor(ServerPO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listServersTest() throws JsonProcessingException {

        String response = "[{\"serverId\":1,\"serverName\":\"Server test1\",\"serverCategory\":\"Test\",\"ownerId\":1,\"channel\":[{\"channelId\":1,\"channelName\":\"General\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[]},{\"channelId\":2,\"channelName\":\"General\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha\",\"pmessageId\":3,\"channelId\":2,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha\",\"pmessageId\":2,\"channelId\":2,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha\",\"pmessageId\":4,\"channelId\":2,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha\",\"pmessageId\":5,\"channelId\":2,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha\",\"pmessageId\":6,\"channelId\":2,\"authorId\":1,\"timestamp\":\"2023-03-01\"}]},{\"channelId\":3,\"channelName\":\"General\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[]},{\"channelId\":5,\"channelName\":\"General\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[]},{\"channelId\":4,\"channelName\":\"General\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[]}],\"members\":[]},{\"serverId\":2,\"serverName\":\"Harmonia's 2nd server\",\"serverCategory\":null,\"ownerId\":1,\"channel\":[],\"members\":[{\"serverMemberId\":2,\"serverId\":2,\"member\":{\"userId\":2,\"username\":\"test2\",\"email\":\"test2@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null,\"timestamp\":\"2023-03-01\"},\"memberId\":2,\"nickName\":\"TestMember\",\"joinDate\":\"2023-03-01\"},{\"serverMemberId\":1,\"serverId\":2,\"member\":{\"userId\":5,\"username\":\"Jihau\",\"email\":\"jiiihau@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null,\"timestamp\":\"2023-03-01\"},\"memberId\":5,\"nickName\":\"Jihau\",\"joinDate\":\"2023-03-01\"}]}]";
        ServerPO[] expectedServers = serverReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedServers, HttpStatus.OK));
        ServerPO[] actualServers =  serverClient.listAllServers();
        assertNotNull(actualServers);
        assertArrayEquals(expectedServers, actualServers);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any());
    }
    @Test
    public void listServersByNameTest() throws JsonProcessingException {

        String response = "[{\"serverId\":2,\"serverName\":\"Harmonia's 2nd server\",\"serverCategory\":null,\"ownerId\":1,\"channel\":[],\"members\":[{\"serverMemberId\":2,\"serverId\":2,\"member\":{\"userId\":2,\"username\":\"test2\",\"email\":\"test2@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null,\"timestamp\":\"2023-03-01\"},\"memberId\":2,\"nickName\":\"TestMember\",\"joinDate\":\"2023-03-01\"},{\"serverMemberId\":1,\"serverId\":2,\"member\":{\"userId\":5,\"username\":\"Jihau\",\"email\":\"jiiihau@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null,\"timestamp\":\"2023-03-01\"},\"memberId\":5,\"nickName\":\"Jihau\",\"joinDate\":\"2023-03-01\"}]}]";
        ServerPO[] expectedServers = serverReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedServers, HttpStatus.OK));
        ServerPO[] actualServers =  serverClient.listServersByName();
        assertNotNull(actualServers);
        assertArrayEquals(expectedServers, actualServers);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any());
    }

    @Test
    public void listServersByCategoryTest() throws JsonProcessingException {

        String response = "[{\"channel\":[],\"members\":[],\"serverId\":2,\"serverName\":\"Another cool server\",\"serverCategory\":\"Stickers\",\"ownerId\":1}]";
        ServerPO[] expectedServers = serverReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedServers, HttpStatus.OK));
        ServerPO[] actualServers =  serverClient.listServersByCategory("Stickers");
        assertNotNull(actualServers);
        assertArrayEquals(expectedServers, actualServers);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ServerPO[]>>any());
    }
}
