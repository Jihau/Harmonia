package com.harmonia.backend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class is used to validate API keys.
 *
 * @version 1.0
 * @Author: Harmonia Team
 */
@Component
public class KeyValidator {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String keywordForApiKey;
    boolean protectEndpoints;

    /**
     * Constructs a KeyValidator instance with a keyword for API key and a boolean value for whether to protect endpoints.
     *
     * @param keywordForApiKey the keyword for API key
     * @param protectEndpoints the boolean value for whether to protect endpoints
     */
    public KeyValidator(@Value("${harmonia.constants.keyword.apiKey}") String keywordForApiKey, @Value("${harmonia.constants.protectEndpoints}") boolean protectEndpoints) {
        this.keywordForApiKey = keywordForApiKey;
        this.protectEndpoints = protectEndpoints;
    }

    public boolean validateAPIKey(String apiKey) {
        return !protectEndpoints || passwordEncoder.matches(keywordForApiKey, apiKey);
    }
}
