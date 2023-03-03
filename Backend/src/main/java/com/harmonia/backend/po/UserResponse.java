package com.harmonia.backend.po;
import com.harmonia.backend.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    Long userId;
    String username;
    String email;
    String profileIcon;
    String bio;

    public UserResponse (User user){
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profileIcon = user.getProfileIcon();
        this.bio = user.getBio();
    }
}
