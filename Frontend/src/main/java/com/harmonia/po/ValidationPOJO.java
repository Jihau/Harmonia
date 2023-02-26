package com.harmonia.po;

/**
 * ValidationPOJO
 */
public class ValidationPOJO {

    String username;
    String password;

    public ValidationPOJO() {

    }

    public ValidationPOJO(String uname, String pword) {
        this.username = uname;
        this.password = pword;
    }

    public void setUsername(String name) {
        username = name;
    }

    public void setPassword(String pword) {
        password = pword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}