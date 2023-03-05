package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


/**
 * Server entity represents a server in the application.
 *
 * @author Harmonia team
 * @version 1.0
 */
@Getter
@Setter
@Entity
@NamedQueries({
        // Retrieves all servers whose name contains the given name
        @NamedQuery(name = "Server.listServersByServerName", query = "FROM Server server where server.serverName like :serverName"),
        // Retrieves all servers in the given category
        @NamedQuery(name = "Server.listServersByServerCategory", query = "FROM Server server where server.serverCategory like :serverCategory"),
        // Retrieves all servers owned by the user with the given ID
        @NamedQuery(name = "Server.listServersByOwnerId", query = "FROM Server server where server.ownerId = :userId")})
@Table(name = "server", schema = "harmoniadb")
public class Server {

    /**
     * The user who owns the server.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "OwnerId", foreignKey = @ForeignKey(name = "Owner_ofServer_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User Owner;

    /**
     * The channels that belong to the server.
     */
    @OneToMany(targetEntity = Channel.class, mappedBy = "server", fetch = FetchType.EAGER)
    Set<Channel> channel;

    /**
     * The members who belong to the server.
     */
    @OneToMany(targetEntity = ServerMember.class, mappedBy = "server", fetch = FetchType.EAGER)
    Set<ServerMember> members;

    /**
     * The unique identifier of the server.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerId", nullable = false)
    private Long serverId;

    /**
     * The name of the server.
     */
    @Column(name = "ServerName", nullable = false)
    private String serverName;

    /**
     * The category of the server.
     */
    @Column(name = "ServerCategory")
    private String serverCategory;

    /**
     * The ID of the user who owns the server.
     */
    @Column(name = "OwnerId", nullable = false)
    private Long ownerId;
}
