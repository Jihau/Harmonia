package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPO {
    int userId;
    String username;
    String email;
    String password;
    String profileIcon;
    String bio;
    Date timestamp;

    @Override
    public String toString() {
        return username;
    }
}
