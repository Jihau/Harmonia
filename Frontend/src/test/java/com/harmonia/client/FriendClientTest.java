package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.ChannelPO;
import com.harmonia.po.FriendPo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendClientTest {

    @InjectMocks
    FriendClient friendClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader friendReader = new ObjectMapper().readerFor(FriendPo[].class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listFriendsTest() throws JsonProcessingException {
        String response = "[{\"userId\":2,\"friendId\":4,\"userFriendId\":8}]";
        FriendPo[] expectedFriends = friendReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<FriendPo[]>>any())).thenReturn(new ResponseEntity<>(expectedFriends, HttpStatus.OK));
        FriendPo[] actualFriends = friendClient.listFriends();
        assertNotNull(actualFriends);
        assertArrayEquals(expectedFriends, actualFriends);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<FriendPo[]>>any());
    }

    @Test
    public void addFriendTest() throws IOException {

            FriendPo request = new FriendPo();
            request.setFriendId(1);
            request.setUserId(2);
            request.setUserFriendId(1);

            String response = "{\"friendId\":1,\"userId\":2,\"userFriendId\":1}";

            Mockito.when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class), Mockito.<HttpEntity<FriendPo>>any(), Mockito.<Class<FriendPo>>any(), Mockito.<Map<String, ?>>any())).thenReturn(new ResponseEntity<>(friendReader.readValue(response, FriendPo.class), HttpStatus.OK));

            FriendPo responseFriendPO = friendClient.addFriend(request);

            assertEquals(1, responseFriendPO.getFriendId());
            assertEquals(2, responseFriendPO.getUserId());
            assertEquals(1, responseFriendPO.getUserFriendId());

    }

    @Test
    public void removeFriendTest() {
        lenient().when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<Void> response = friendClient.removeFriend(1);
        verify(restTemplate, times(1)).exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



}
