package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        // Retrieves all servers by member ID.
        @NamedQuery(name = "Friend.listFriendsByUserId", query = "FROM Friend f where f.userId = : userId")})
@Table(name = "user_friends", schema = "harmoniadb", uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUserFriend", columnNames = { "UserId", "FriendId" })})

public class Friend {

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("UserId")
    @JsonIgnore
    @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "User_friend_user_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User user;

    @Column(name = "UserId", nullable = false)
    Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("UserId")
    @JsonIgnore
    @JoinColumn(name = "FriendId", foreignKey = @ForeignKey(name = "User_friend_friend_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User friend;

    @Column(name = "FriendId", nullable = false)
    Long friendId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "UserFriendId")
    private Long userFriendId;
}

