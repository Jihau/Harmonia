package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**

 This class represents a user entity in the application. It contains the basic information about a user, such as their

 username, email, password, profile icon, and bio. It also contains the timestamp for when the user was created.

 The class is annotated with JPA annotations to define the mapping between the class and the database table. It is also

 annotated with Lombok annotations to automatically generate boilerplate code, such as getters and setters.
 */
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
    /**
     * The user's unique identifier with key value
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserId", nullable = false)
    private Long userId;

    /**
     * The user's username which cannot be null and is unique.
     */
    @Basic
    @Column(name = "Username", unique = true,nullable = false)
    private String username;

    /**
     * the user's email which cannot be null and is unique.
     */
    @Basic
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * the user's password.It is not visible to other users and cannot be null.
     */
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * the user's profile icon in URL format which cannot be null
     */
    @Basic
    @Column(name = "Profile_icon", nullable = false)
    private String profileIcon;

    /**
     * A short bio or description of the user. It is optional and can be up to 200 characters long.
     */
    @Basic
    @Column(name = "Bio", length = 200)
    private String bio;

    /**
     * The timestamp for when the user was created. It is automatically generated by the database.
     */
    @Column(name = "Created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false)
    @Generated(GenerationTime.INSERT)
    Date timestamp;
}
