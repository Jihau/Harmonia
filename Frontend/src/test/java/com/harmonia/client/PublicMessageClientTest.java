package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.ChannelPO;
import com.harmonia.po.PublicMessagePO;
import jdk.security.jarsigner.JarSignerException;
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
public class PublicMessageClientTest {
    @InjectMocks
    PublicMessageClient publicMessageClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader publicMessagereader = new ObjectMapper().readerFor(PublicMessagePO[].class);

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listPublicMessagesTest() throws JsonProcessingException {
        String response = "[{\"messageText\":\"hello hello hello!!!! is anyone hereeeee Wahahahaha(edited)\",\"pmessageId\":1,\"channelId\":1,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"Who the heck are you !!!\",\"pmessageId\":2,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"Third message\",\"pmessageId\":3,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"}]";
        PublicMessagePO[] expectedPublicMessages = publicMessagereader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<PublicMessagePO[]>>any())).thenReturn(new ResponseEntity<>(expectedPublicMessages, HttpStatus.OK));
        PublicMessagePO[] actualPublicMessages = publicMessageClient.getAllMessages();
        System.out.println(actualPublicMessages);
        System.out.println(expectedPublicMessages);
        assertNotNull(actualPublicMessages);
        assertArrayEquals(expectedPublicMessages, actualPublicMessages);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<PublicMessagePO[]>>any());

    }



}
