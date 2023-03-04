package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.harmonia.po.ChannelPO;
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
public class ChannelClientTest {
    @InjectMocks
    ChannelClient channelClient;

    @Mock
    RestTemplate restTemplate;

    ObjectReader channelReader = new ObjectMapper().readerFor(ChannelPO[].class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listChannelsTest() throws JsonProcessingException {
        String response = "[{\"channelId\":1,\"channelName\":\"TestChannel\",\"timestamp\":\"2023-03-01\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":[{\"messageText\":\"Third message\",\"pmessageId\":3,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"Hellooooo everyone!!!!!\",\"pmessageId\":1,\"channelId\":1,\"authorId\":1,\"timestamp\":\"2023-03-01\"},{\"messageText\":\"Who the heck are you !!!\",\"pmessageId\":2,\"channelId\":1,\"authorId\":2,\"timestamp\":\"2023-03-01\"}]}]";
        ChannelPO[] expectedChannels = channelReader.readValue(response);

        Mockito.when(restTemplate.exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ChannelPO[]>>any())).thenReturn(new ResponseEntity<>(expectedChannels, HttpStatus.OK));
        ChannelPO[] actualChannels = channelClient.listAllChannels();
        assertNotNull(actualChannels);
        assertArrayEquals(expectedChannels, actualChannels);
        verify(restTemplate, times(1)).exchange(anyString(), any(), Mockito.any(), Mockito.<Class<ChannelPO[]>>any());

    }

    @Test
    public void addChannelTest() throws IOException {
        // Setup
        ChannelPO request = new ChannelPO();
        request.setChannelName("Test channel");
        request.setChannelType("Text");
        request.setServerId(1);

        String response = "{\"channelId\":4,\"channelName\":\"Test channel\",\"timestamp\":\"2023-03-02\",\"channelType\":\"Text\",\"serverId\":1,\"publicMessages\":null}";

        // Mock the HTTP request
        Mockito.when(restTemplate.exchange(anyString(), Mockito.any(HttpMethod.class), Mockito.<HttpEntity<ChannelPO>>any(), Mockito.<Class<ChannelPO>>any(), Mockito.<Map<String, ?>>any())).thenReturn(new ResponseEntity<>(channelReader.readValue(response, ChannelPO.class), HttpStatus.OK));

        // Call the method under test
        ChannelPO responseChannelPO = channelClient.addChannel(request);

        // Verify the response
        assertEquals(request.getChannelName(), responseChannelPO.getChannelName());

        // Verify that the HTTP request was made with the expected arguments
        ArgumentCaptor<HttpEntity<ChannelPO>> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
        Mockito.verify(restTemplate, Mockito.times(1)).exchange(anyString(), Mockito.eq(HttpMethod.POST), httpEntityCaptor.capture(), Mockito.eq(ChannelPO.class), Mockito.eq(Collections.emptyMap()));
        HttpEntity<ChannelPO> capturedHttpEntity = httpEntityCaptor.getValue();
        ChannelPO capturedRequest = capturedHttpEntity.getBody();

        assertEquals(request.getChannelName(), capturedRequest.getChannelName());
        assertEquals(request.getChannelType(), capturedRequest.getChannelType());
        assertEquals(request.getServerId(), capturedRequest.getServerId());
    }

    @Test
    public void deleteChannelTest() {
        lenient().when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
        ResponseEntity<Void> response = channelClient.removeChannel(1);
        verify(restTemplate, times(1)).exchange(anyString(), Mockito.any(), Mockito.<HttpEntity<Void>>any(), Mockito.<Class<Void>>any(), Mockito.anyMap());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void editChannelTest() {
        ChannelPO request = new ChannelPO();
        request.setChannelId(3);
        request.setChannelName("Tested channel");
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), Mockito.<Class<ChannelPO>>any(), anyMap())).thenReturn(new ResponseEntity<>((request), HttpStatus.OK));
        ChannelPO responseChannelPO = channelClient.editChannel(request);
        assertEquals(request.getChannelName(), responseChannelPO.getChannelName());
    }
}



