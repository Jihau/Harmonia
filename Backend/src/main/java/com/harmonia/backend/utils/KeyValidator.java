package com.harmonia.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class KeyValidator {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String keywordForApiKey;
    boolean protectEndpoints;

    public KeyValidator(@Value("${harmonia.constants.keyword.apiKey}") String keywordForApiKey, @Value("${harmonia.constants.protectEndpoints}") boolean protectEndpoints) {
        this.keywordForApiKey = keywordForApiKey;
        this.protectEndpoints = protectEndpoints;
    }

    public boolean validateAPIKey(String apiKey) {
        return !protectEndpoints || passwordEncoder.matches(keywordForApiKey, apiKey);
    }
}
