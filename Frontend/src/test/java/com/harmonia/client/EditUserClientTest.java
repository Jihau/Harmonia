package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.harmonia.po.UserPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class EditUserClientTest {
    @InjectMocks
    UserClient userClient;

    @Mock
    RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void editUserTest()  {
        UserPO request = new UserPO();
        request.setUserId(1);
        request.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), Mockito.<Class<UserPO>>any(), anyMap()))
                .thenReturn(new ResponseEntity<>((request), HttpStatus.OK));
        UserPO responseUserPO = userClient.editIcon(request);
        assertEquals(request.getProfileIcon(), responseUserPO.getProfileIcon());
    }

    @Test
    public void editUserBioTest()  {
        UserPO request = new UserPO();
        request.setUserId(1);
        request.setBio("I like cats");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), Mockito.<Class<UserPO>>any(), anyMap()))
                .thenReturn(new ResponseEntity<>((request), HttpStatus.OK));
        UserPO responseUserPO = userClient.editBio(request);
        assertEquals(request.getBio(), responseUserPO.getBio());
    }
}
