package com.harmonia.backend.domain;

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
@Table(name = "tbl_server", schema = "harmoniadb")
public class Server {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerId")
    private Long serverId;
    @Column(name = "ServerName")
    private String serverName;
    @Column(name = "ServerCategory")
    private String serverCategory;
    @JsonProperty("OwnerId")
    @Column(name = "OwnerId")
    private int ownerId;
    @OneToMany(targetEntity = Channel.class, mappedBy = "server", fetch = FetchType.EAGER)
    Set<Channel> channel;
}
