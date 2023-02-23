package com.harmonia.client;

import com.harmonia.po.UserPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserClientTest {
    UserClient userClient;

    @BeforeEach
    public void loadVariables(){
        userClient = UserClient.getInstance();
    }

    @Test
    public void addUserTest() {
        UserPO userPO = new UserPO();
        userPO.setUsername("JihauTest1");
        userPO.setEmail("lihau@test1.com");
        userPO.setPassword("very123");
        userPO.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        UserPO responseUserPO = userClient.addUser(userPO);
        assertEquals(responseUserPO.getUsername(),userPO.getUsername());
    }

    @Test
    public void listUsersTest() {
        String response = userClient.listUsers();
        assertNotEquals(response.length(),0);
    }

    @Test
    public void deleteUserTest() {
        UserPO userPO = new UserPO();
        userPO.setUsername("JihauTest2");
        userPO.setEmail("lihau@test2.com");
        userPO.setPassword("very123");
        userPO.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        userPO.setUserId(21);
        UserPO responseUser = userClient.addUser(userPO);


        ResponseEntity<UserPO> response = userClient.removeUser(responseUser);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
