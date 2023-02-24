package com.harmonia.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.harmonia.po.UserPO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditUserClientTest {
  /*  @InjectMocks
    UserClient userClient;
      */
    UserClient userClient = new UserClient();
    @Test
    public void editUserTest() throws JsonProcessingException {
        UserPO request = new UserPO();
        request.setUserId(1);
        request.setProfileIcon("https://i.imgur.com/yfhVP8e.png");
        UserPO responseUserPO = userClient.editUser(request);
        assertEquals(request.getProfileIcon(), responseUserPO.getProfileIcon());
    }
}
