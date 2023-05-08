package com.harmonia.websockets;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.Scanner;

/** This class is used to start the web socket.
 * @author Team Harmonia
 * @version 2.0
 */
public class StompWebSocketClient {

    /** This method starts the web socket.
     */
    public static void startWebSocket() {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSessionHandler sessionHandler = new StompClientSessionHandler();
        final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.add(HarmoniaConstants.API_KEY_HEADER_NAME, HarmoniaData.KEY_FOR_HARMONIA_BACK_END);
        stompClient.connect(HarmoniaConstants.SOCKETS_URL,headers, sessionHandler);
        new Scanner(System.in).nextLine();
    }
}
