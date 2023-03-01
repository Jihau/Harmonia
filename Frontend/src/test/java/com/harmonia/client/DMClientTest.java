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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DMClientTest {
    @InjectMocks
    DirectMessageClient directMessacgeClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader DM_POReader = new ObjectMapper().readerFor(MessagePO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listUsersTest() throws JsonProcessingException {

        String response = "[{\"dmessageId\":1,\"messageText\":\"asdawdsa\",\"timestamp\":\"2023-02-28\",\"recipientId\":2},{\"dmessageId\":2,\"messageText\":\"wtf\",\"timestamp\":\"2023-02-28\",\"recipientId\":3},{\"dmessageId\":3,\"messageText\":\"This is a test message 3 and its been edited\",\"timestamp\":\"2023-02-28\",\"recipientId\":2},{\"dmessageId\":4,\"messageText\":\"Hello, I am a message with an author and recipient test\",\"timestamp\":\"2023-02-28\",\"recipientId\":1},{\"dmessageId\":5,\"messageText\":\"Hello, I am a message with an author and recipient test\",\"timestamp\":\"2023-02-28\",\"recipientId\":1},{\"dmessageId\":6,\"messageText\":\"Hello, I am a message superman\",\"timestamp\":\"2023-02-28\",\"recipientId\":3},{\"dmessageId\":7,\"messageText\":\"Hello, I am a asdniawudasunda\",\"timestamp\":\"2023-02-28\",\"recipientId\":3}]";
        MessagePO[] expectedMessages = DM_POReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<MessagePO[]>>any()))
                .thenReturn(new ResponseEntity<>(expectedMessages, HttpStatus.OK));
        MessagePO[] actualMessages = directMessacgeClient.getAllMessages();
        assertNotNull(actualMessages);
        assertArrayEquals(expectedMessages, actualMessages);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<MessagePO[]>>any());
    }


}
