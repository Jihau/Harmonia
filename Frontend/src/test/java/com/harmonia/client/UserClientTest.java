package com.harmonia.client;

import com.harmonia.po.UserPO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserClientTest {
    UserClient userClient;

    @BeforeEach
    public void loadVariables(){
        userClient = new UserClient();
    }

    @Test
    public void addUserTest() {
        UserPO userPO = new UserPO();
        userPO.setUsername("JihauTest3");
        userPO.setEmail("lihau@test5.com");
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
        userPO.setUsername("JihauTest3");
        userPO.setEmail("lihau@test5.com");
        userPO.setPassword("very123");
        userPO.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        UserPO responseUserPO = userClient.addUser(userPO);


        int response = userClient.removeUser(userPO).getStatusCode().value();

        assertEquals(200, response);
    }
}
