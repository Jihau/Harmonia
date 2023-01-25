package com.example.harmonia.controller;

import com.example.harmonia.model.User;
import com.example.harmonia.view.LoginView;

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