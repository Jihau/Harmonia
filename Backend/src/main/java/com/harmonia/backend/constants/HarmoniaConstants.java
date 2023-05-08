package com.harmonia.backend.constants;

import org.springframework.messaging.simp.stomp.StompSession;

/**
 * A class containing constants used throughout the application.
 * Author: team Harmonia
 * Version: 2.0
 */
public class HarmoniaConstants {
    public static final String API_KEY_HEADER_NAME = "Harmonia-api-key";
    public static StompSession STOMP_SESSION = null;

    public static final String SOCKETS_URL = "ws://localhost/chat";

    public static String KEY_FOR_HARMONIA_BACK_END = "";

    public static String KEYWORD_GENERATE_KEY = "ha6BrHz1/LYT5f8NX+WM5pmMHH8PLzF+fC12vavd/XE=";

}
