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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

import static com.harmonia.constants.HarmoniaConstants.USERS_LIST_URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserClientTest {
    @InjectMocks
    UserClient userClient;

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
        UserPO user1 = new UserPO();
        user1.setUsername("ArrayTest");
        user1.setEmail("lihau@Arraytest21.com");
        user1.setPassword("ArrayTest123");
        user1.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        UserPO[] expectedUsers = new UserPO[] { user1, new UserPO() };
        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<UserPO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedUsers, HttpStatus.OK));
        UserPO[] actualUsers = userClient.listUsers();
        assertNotNull(actualUsers);
        assertArrayEquals(expectedUsers, actualUsers);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<UserPO[]>>any());
    }

    @Test
    public void deleteUserTest() {
        lenient().when(restTemplate.exchange(anyString(),
                Mockito.any(),
                Mockito.<HttpEntity<Void>>any(),
                Mockito.<Class<Void>>any(),
                Mockito.anyMap())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<Void> response = userClient.removeUser(1);
        verify(restTemplate, times(1)).exchange(anyString(),
                Mockito.any(),
                Mockito.<HttpEntity<Void>>any(),
                Mockito.<Class<Void>>any(),
                Mockito.anyMap());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void loginUserTset() throws JsonProcessingException {
        /*
        lenient().when(restTemplate.exchange(anyString(),
                Mockito.any(),
                Mockito.<HttpEntity<Void>>any(),
                Mockito.<Class<Void>>any(),
                Mockito.anyMap())).thenReturn(new ResponseEntity<>(HttpStatus.OK));

        String expectedEesponse = "{\"userId\":3,\"username\":\"jokke\",\"email\":\"asdsadawdsad@gmail.com\",\"profileIcon\":\"https://i.imgur.com/yfhVP8e.png\",\"bio\":null}";
        UserPO expected = userPOReader.readValue(expectedEesponse);
        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<UserPO>>any()))
                .thenReturn(new ResponseEntity<>(expected, HttpStatus.OK));
        ResponseEntity<UserPO> validated = userClient.validate("jokke", "jokke123");
        UserPO gotUser = validated.getBody();

        assertNotNull(validated);
        assertEquals(HttpStatus.OK, validated.getStatusCode());
        */
    }
}
