package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.UserPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserClientTest {
  /*  @InjectMocks
    UserClient userClient;
      */
    UserClient userClient = new UserClient();

    @Mock
    RestTemplate restTemplate;

    ObjectReader userPOReader = new ObjectMapper().readerFor(UserPO.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addUserTest() throws JsonProcessingException {
        UserPO request = new UserPO();
        request.setUsername("JihauTest121");
        request.setEmail("lihau@test21.com");
        request.setPassword("very123");
        request.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        String responseFromEndpoint = "{\"userId\":1,\"username\":\"JihauTest121\",\"email\":\"lihau@test21.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\"}";
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class)))
                .thenReturn(new ResponseEntity<>((UserPO) userPOReader.readValue(responseFromEndpoint), HttpStatus.OK));
        UserPO responseUserPO = userClient.addUser(request);
        assertEquals(request.getUsername(), responseUserPO.getUsername());
    }

    @Test
    public void listUsersTest() {
    }

    @Test
    public void deleteUserTest() {
  /*      ResponseEntity<Void> response = userClient.removeUser(2);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class), any(Map.class)))
                .thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
        assertEquals(HttpStatus.OK, response.getStatusCode());
   */
    }
}
