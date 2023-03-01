package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name="Server.listServersByServerName", query = "FROM Server server where server.serverName like :serverName")
})
@Table(name = "server", schema = "harmoniadb")
public class Server {


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "OwnerId", foreignKey = @ForeignKey(name = "Owner_ofServer_fk", value = ConstraintMode.CONSTRAINT),nullable = false)
    User Owner;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerId",nullable = false)
    private Long serverId;
    @Column(name = "ServerName",nullable = false)
    private String serverName;
    @Column(name = "ServerCategory")
    private String serverCategory;
    @JsonProperty("OwnerId")
    @Column(name = "OwnerId",nullable = false)
    private Long ownerId;
    @OneToMany(targetEntity = Channel.class, mappedBy = "server", fetch = FetchType.EAGER)
    Set<Channel> channel;
    @OneToMany(targetEntity = ServerMember.class, mappedBy = "server", fetch = FetchType.EAGER)
    Set<ServerMember> members;
}
