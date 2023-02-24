package com.harmonia.backend.serviceTests;

import com.harmonia.backend.domain.User;
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

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("testJihau");
        user.setEmail("testJihau@test.com");
        user.setPassword("testjihau");
        user.setProfileIcon("testjihau");

        User userResponse = new User();
        userResponse.setUserId(1L);
        userResponse.setUsername("testJihau");
        userResponse.setEmail("testJihau@test.com");
        userResponse.setPassword("testjihau");
        userResponse.setProfileIcon("testjihau");

        when(userRepository.findByUsername(userResponse.getUsername())).thenReturn(null);
        when(userRepository.save(any())).thenReturn(userResponse);

        UserResponse savedUser = userService.createUser(userResponse);

        assertEquals(userResponse.getUsername(), savedUser.getUsername());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}
