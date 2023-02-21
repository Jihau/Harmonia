package com.harmonia.po;
/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 */
import java.io.Serializable;
/*
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 */
import java.lang.module.ModuleDescriptor.Exports;
public class UserPO implements Serializable {
    int userId;
    String username;
    String email;
    String password;
    String profileIcon;

    public UserPO() {        
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
    Exports po;
}
