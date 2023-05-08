package com.harmonia.websockets;

import com.harmonia.utils.HarmoniaDataLoader;
import javafx.application.Platform;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.Map;

/** This class is used to handle the stomp session.
 * @author Team Harmonia
 * @version 2.0
 */
public class StompClientSessionHandler extends StompSessionHandlerAdapter {

    /**
     * This method is called when a new session is established.
     *
     * @param session           The stomp session.
     * @param connectedHeaders  The connected headers.
     */
    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        System.out.println("New session established. Session Id -> :" + session.getSessionId());
        session.subscribe("/topic/messages", this);
    }

    /**
     * This method is called when a frame is received.
     *
     * @param headers           The stomp headers.
     * @param payload           The payload.
     */
    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        System.out.println("Payload:" + payload);
        Platform.runLater(() -> HarmoniaDataLoader.loadDirectMessagesByUserId(true));

    }

    /**
     * This method returns the payload type.
     *
     * @param headers           The stomp headers.
     * @return                  The payload type.
     */
    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Map.class;
    }
}
