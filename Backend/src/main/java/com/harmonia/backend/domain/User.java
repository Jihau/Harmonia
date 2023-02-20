package com.harmonia.backend.domain;

import com.harmonia.backend.repository.UserRepository;
import com.harmonia.backend.service.UserService;
import jakarta.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name="User.listUsersByUserName", query = "FROM User u WHERE u.username LIKE :username")
})
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserId")
    private Long userId;
    @Basic
    @Column(name = "Username", unique = true)
    private String username;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "Profile_icon")
    private String profileIcon;


    public User() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (profileIcon != null ? !profileIcon.equals(user.profileIcon) : user.profileIcon != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(userId);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (profileIcon != null ? profileIcon.hashCode() : 0);
        return result;
    }
}
