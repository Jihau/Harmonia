package com.harmonia.backend.websockets;

import com.harmonia.backend.constants.HarmoniaConstants;
import com.harmonia.backend.utils.HarmoniaUtils;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import static com.harmonia.backend.constants.HarmoniaConstants.SOCKETS_URL;

public class StompWebSocketClient {

    public static void startWebSocket() throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSessionHandler sessionHandler = new StompClientSessionHandler();
        final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add(HarmoniaConstants.API_KEY_HEADER_NAME, HarmoniaUtils.generateBackEndKey());
        HarmoniaConstants.STOMP_SESSION = stompClient.connect(SOCKETS_URL, headers, sessionHandler).get();
        new Scanner(System.in).nextLine();
    }
}
