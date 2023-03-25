package com.harmonia.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        // Retrieves all servers by member ID.
        @NamedQuery(name = "Friend.listFriendsByUserId", query = "FROM Friend friend where friend.friendId = : friendId")})
@Table(name = "user_friends", schema = "harmoniadb")

public class Friend {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("UserId")
    @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "User_friend_user_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User user;

    @Transient
    String userName;

    @Column(name = "UserId", nullable = false)
    Long userId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "FriendId")
    private Long friendId;

    public String getUserName(){
        return user.getUsername();
    }
}

