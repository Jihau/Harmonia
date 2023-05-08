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

/**
 * This class Represents a client for interacting with the web socket.
 * @version 2.0
 * @author  Harmonia Team
 */
public class StompWebSocketClient {

    /**
     * Starts the web socket.
     * @throws ExecutionException if the execution fails.
     * @throws InterruptedException if the execution is interrupted.
     */
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
