package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "User.listUsersByUserName", query = "FROM User u WHERE u.username LIKE :username")
})
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserId", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "Username", unique = true,nullable = false)
    private String username;
    @Basic
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;
    @Basic
    @Column(name = "Profile_icon", nullable = false)
    private String profileIcon;
}
