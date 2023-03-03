package com.harmonia.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Configuration class for user-related beans.
 */
@Configuration
public class UserConfig {
    /**
     * Bean definition for BCryptPasswordEncoder.
     *
     * @return an instance of BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


