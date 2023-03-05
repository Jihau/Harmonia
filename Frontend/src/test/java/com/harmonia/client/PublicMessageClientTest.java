package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.PublicMessagePO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicMessageClientTest {
    @InjectMocks
    PublicMessageClient publicMessageClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader publicMessageReader = new ObjectMapper().readerFor(PublicMessagePO.class);
    ObjectReader listPublicMessageReader = new ObjectMapper().readerFor(PublicMessagePO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listPublicMessagesTest() throws JsonProcessingException {
        String response = "[{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha(edited)\",\"pmessageId\":1,\"channelId\":1,\"authorId\":1,\"timestamp\":\"2023-03-01\"}," +
                "{\"messageText\":\"Who the heck are you !!!\",\"pmessageId\":2,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"}," +
                "{\"messageText\":\"Third message\",\"pmessageId\":3,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"}]";
        PublicMessagePO[] expectedPublicMessages = listPublicMessageReader.readValue(response);
        when(restTemplate.exchange(anyString(), any(), any(), (Class) any()))
                .thenReturn(new ResponseEntity<>(expectedPublicMessages, HttpStatus.OK));
        PublicMessagePO[] actualPublicMessages = publicMessageClient.getAllMessages();
        verify(restTemplate, times(1)).exchange(anyString(), any(), any(), (Class) any());
        assertNotNull(actualPublicMessages);
        assertArrayEquals(expectedPublicMessages, actualPublicMessages);
    }

    @Test
    public void sendPublicMessage() throws JsonProcessingException {
        PublicMessagePO request = new PublicMessagePO();
        request.setMessageText("Test test test!");
        request.setAuthorId(1L);
        request.setChannelId(1L);

        String responseFromEndpoint = "{\"messageText\":\"" + request.getMessageText() + "\",\"pmessageId\":5,\"channelId\":1,\"authorId\":1,\"timestamp\":\"2023-03-04\"}";
        lenient().when(restTemplate.exchange(Mockito.<String>any(), Mockito.any(), Mockito.<HttpEntity<PublicMessagePO>>any(), Mockito.<Class<PublicMessagePO>>any()))
                .thenReturn(new ResponseEntity<>((PublicMessagePO) publicMessageReader.readValue(responseFromEndpoint), HttpStatus.OK));
        PublicMessagePO responsePublicMessagePO = publicMessageClient.sendPublicMessage(request);
        assertEquals(request.getMessageText(), responsePublicMessagePO.getMessageText());
    }

    @Test
    public void removePublicMessageTest() {
        lenient().when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<Void> response = publicMessageClient.removeMessage(3L);
        verify(restTemplate, times(1)).exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void editPublicMessageTest() {
        
        PublicMessagePO request = new PublicMessagePO();
        request.setPmessageId(3L);
        request.setMessageText("Test updating message!");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), Mockito.<Class<PublicMessagePO>>any(), anyMap())).thenReturn(new ResponseEntity<>((request), HttpStatus.OK));
        String responsePublicMessagePO = publicMessageClient.editPublicMessage(request).getBody();
        assertEquals("Message has been edited", responsePublicMessagePO);
         
    }
}
