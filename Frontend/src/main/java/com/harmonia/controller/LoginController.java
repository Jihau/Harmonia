package com.harmonia.controller;

import com.harmonia.model.User;
import com.harmonia.view.LoginView;

public class LoginController {
    private LoginView view;
    private User user;

    public LoginController(LoginView view) {
        this.view = view;
    }

    public boolean login(String username, String password) {
        return false;
        // check the credentials with the user model and return the result
    }
}