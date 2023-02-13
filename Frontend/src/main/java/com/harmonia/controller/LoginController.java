package com.harmonia.controller;

import com.harmonia.po.UserPO;
import com.harmonia.view.LoginView;

public class LoginController {
    private LoginView view;
    private UserPO user;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public boolean login(String username, String password) {
        return false;
        // check the credentials with the user model and return the result
    }
}