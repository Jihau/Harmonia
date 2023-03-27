package com.harmonia.backend.utils;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class KeyValidatorTest {
    private final String keyword = "TestingWordKey";

    @Test
    void protectEndPointsFlagToFalseReturnsTrue() {
        KeyValidator keyValidator = new KeyValidator(keyword, false);
        assertTrue(keyValidator.validateAPIKey(keyword));
    }

    @Test
    void protectEndPointsFlagValidatesKey() {
        String keyGenerated = BCrypt.hashpw(keyword, BCrypt.gensalt());
        KeyValidator keyValidator = new KeyValidator(keyword, true);
        assertTrue(keyValidator.validateAPIKey(keyGenerated));
    }

    @Test
    void protectEndPointsFlagValidatesKeyWithWrongKeyword() {
        String keyGenerated = BCrypt.hashpw("fakeKeyword", BCrypt.gensalt());
        KeyValidator keyValidator = new KeyValidator(keyword, true);
        assertFalse(keyValidator.validateAPIKey(keyGenerated));
    }
}
