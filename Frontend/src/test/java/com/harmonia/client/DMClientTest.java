package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.MessagePO;
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

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DMClientTest {
    @InjectMocks
    DirectMessageClient directMessacgeClient = new DirectMessageClient();

    @Mock
    RestTemplate restTemplate;

    ObjectReader DM_POReader = new ObjectMapper().readerFor(MessagePO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listDirectMessagesTest() throws JsonProcessingException {

        String response = "[{\"dmessageId\":1,\"messageText\":\"asdawdsa\",\"timestamp\":\"2023-02-28\",\"recipientId\":2},{\"dmessageId\":2,\"messageText\":\"wtf\",\"timestamp\":\"2023-02-28\",\"recipientId\":3},{\"dmessageId\":3,\"messageText\":\"This is a test message 3 and its been edited\",\"timestamp\":\"2023-02-28\",\"recipientId\":2},{\"dmessageId\":4,\"messageText\":\"Hello, I am a message with an author and recipient test\",\"timestamp\":\"2023-02-28\",\"recipientId\":1},{\"dmessageId\":5,\"messageText\":\"Hello, I am a message with an author and recipient test\",\"timestamp\":\"2023-02-28\",\"recipientId\":1},{\"dmessageId\":6,\"messageText\":\"Hello, I am a message superman\",\"timestamp\":\"2023-02-28\",\"recipientId\":3},{\"dmessageId\":7,\"messageText\":\"Hello, I am a asdniawudasunda\",\"timestamp\":\"2023-02-28\",\"recipientId\":3}]";
        MessagePO[] expectedMessages = DM_POReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<MessagePO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedMessages, HttpStatus.OK));
        MessagePO[] actualMessages = directMessacgeClient.getAllMessages();
        assertNotNull(actualMessages);
        assertArrayEquals(expectedMessages, actualMessages);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<MessagePO[]>>any());
    }

    @Test
    public void editDirectMessageTest() throws JsonProcessingException {
/*
        String editedMessage = "[{\"dmessageId\":1,\"messageText\":\"Edited message for testing\"}]";
        MessagePO expectedEditedMessage = DM_POReader.readValue(editedMessage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(expectedEditedMessage, headers);
        Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("DmessageId", String.valueOf(expectedEditedMessage.getDmessageId()));
        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<MessagePO>>any()))
                .thenReturn(new ResponseEntity<>(expectedEditedMessage, HttpStatus.OK));
        ResponseEntity<?> actualEditedMessage = directMessacgeClient.editMessage(expectedEditedMessage);
        assertNotNull(actualEditedMessage);
        assertEquals(HttpStatus.OK, actualEditedMessage.getStatusCode());
        assertEquals(expectedEditedMessage, actualEditedMessage.getBody());
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.PUT), eq(request),
                eq(MessagePO.class), eq(urlParameters));
*/
    }

    @Test
    public void removeDirectMessageTest() throws JsonProcessingException {
        /* 
        MessagePO removeMe = new MessagePO();
        removeMe.setDmessageId(2);
        removeMe.setMessageText("This is a test message 5");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MessagePO> request = new HttpEntity<>(removeMe, headers);
        Map<String, String> urlParameters = new HashMap<>();


        ResponseEntity<?> not = restTemplate.exchange("http://localhost:8080/dmessage", HttpMethod.DELETE, request, void.class);

        ResponseEntity<?> response = directMessacgeClient.removeMessage(removeMe);
        */
    }
}
