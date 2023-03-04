package com.harmonia.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HarmoniaUtils {

    public static HttpHeaders generateRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
