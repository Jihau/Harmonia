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
 * The ServerMember class represents a user who is a member of a server in the Harmonia messaging platform.
 * Each ServerMember object contains information about the user's membership, including the server they belong to,
 * their member ID, their nickname in the server, and the date they joined the server.
 *
 * @author Harmonia team
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        // Retrieves all servers by member ID.
        @NamedQuery(name = "ServerMember.listServersByMemberId", query = "FROM ServerMember serverMember where serverMember.memberId = : memberId"),
        // Retrieves all server members by server ID.
        @NamedQuery(name = "ServerMember.listServerMembersByServerId", query = "FROM ServerMember serverMember where serverMember.serverId = : serverId")})
@Table(name = "server_members", schema = "harmoniadb")

public class ServerMember {

    /**
     * The server that this member belongs to.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ServerId")
    @JoinColumn(name = "ServerId", foreignKey = @ForeignKey(name = "Server_member_server_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Server server;

    /**
     * The name of the server that this member belongs to.
     */
    @Transient
    String serverName;

    /**
     * The ID of the server that this member belongs to.
     */
    @Column(name = "ServerId", nullable = false)
    Long serverId;

    /**
     * The user who is a member of this server.
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "MemberId", foreignKey = @ForeignKey(name = "Server_member_user_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User member;

    /**
     * The ID of the user who is a member of this server.
     */
    @Column(name = "MemberId", nullable = false)
    Long memberId;

    /**
     * The unique ID of this server member.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerMemberId")
    private Long serverMemberId;

    /**
     * The nickname of this server member.
     */
    @Column(name = "NickName")
    private String nickName;

    /**
     * The date that this server member joined the server.
     */
    @Column(name = "JoinDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date joinDate;

    /**
     * Returns the name of the server associated with this server member.
     *
     * @return The name of the server.
     */
    public String getServerName() {
        return server.getServerName();
    }
}
