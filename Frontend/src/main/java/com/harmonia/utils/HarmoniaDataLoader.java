package com.harmonia.utils;

import com.harmonia.client.UserClient;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.po.UserPO;

public class HarmoniaDataLoader {
    private static final UserClient userClient = new UserClient();

    public static void searchUserByUsername(String userName) {
        UserPO[] users = userClient.listUsers();
        HarmoniaData.USERS_LIST.clear();
        for (int i = 0; i < users.length; i++) {
            HarmoniaData.USERS_LIST.add(users[i]);
        }
    }
}
