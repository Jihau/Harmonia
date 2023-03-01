package com.harmonia.po;

import java.io.Serializable;
import java.util.Date;

public class UserPO implements Serializable {
    int userId;
    String username;
    String email;
    String password;
    String profileIcon;
    String bio;
    Date timestamp;

    public UserPO() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(String profileIcon) {
        this.profileIcon = profileIcon;
    }
}
