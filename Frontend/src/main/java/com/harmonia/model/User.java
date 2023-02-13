package com.harmonia.model;

public class User {
    private int userID;
    private String username;
    private String password;
    private String email;
    private String profileIcon;


    public User(int userID, String username, String password, String email, String profileIcon) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileIcon = profileIcon;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }
}