package com.harmonia.backend.serviceTests;

import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.CreateUserRequest;
import com.harmonia.backend.po.UserResponse;
import com.harmonia.backend.repository.UserRepository;
import com.harmonia.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the UserService class.
 * @author Harmonia Team
 */
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    /**
     * Test method for createUser.
     * Verifies that a user can be created successfully and that the created user has the correct properties.
     */
    @Test
    void testCreateUser() {
        CreateUserRequest user = new CreateUserRequest();
        user.setUsername("testJihau");
        user.setEmail("testJihau@test.com");
        user.setPassword("testjihau");

        User userResponse = new User();
        userResponse.setUserId(1L);
        userResponse.setUsername("testJihau");
        userResponse.setEmail("testJihau@test.com");
        userResponse.setPassword("testjihau");
        userResponse.setProfileIcon("testjihau");

        when(userRepository.findByUsername(userResponse.getUsername())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(userResponse);

        UserResponse savedUser = userService.createUser(user);

        assertEquals(userResponse.getUsername(), savedUser.getUsername());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
