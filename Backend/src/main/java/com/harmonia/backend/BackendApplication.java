package com.harmonia.backend;

import com.harmonia.backend.websockets.StompWebSocketClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

/**
 * The main entry point of the Harmonia backend application.
 * This class starts the Spring Boot application and initializes the Spring application context.
 *
 * @author Harmonia Team
 * @version 2.0
 */
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        new Thread(() -> {
            try {
                StompWebSocketClient.startWebSocket();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
