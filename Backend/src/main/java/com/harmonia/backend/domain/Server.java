package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Server {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerId")
    private Long serverId;
    @Basic
    @Column(name = "ServerName")
    private String serverName;
    @Basic
    @Column(name = "ServerCategory")
    private String serverCategory;
    @JsonProperty("OwnerId")
    @Column(name = "OwnerId")
    private int ownerId;

    public Long getServerId() {
        return serverId;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getServerCategory() {
        return serverCategory;
    }

    public void setServerCategory(String serverCategory) {
        this.serverCategory = serverCategory;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Server server = (Server) o;

        if (serverId != server.serverId) return false;
        if (ownerId != server.ownerId) return false;
        if (serverName != null ? !serverName.equals(server.serverName) : server.serverName != null) return false;
        if (serverCategory != null ? !serverCategory.equals(server.serverCategory) : server.serverCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(serverId);
        result = 31 * result + (serverName != null ? serverName.hashCode() : 0);
        result = 31 * result + (serverCategory != null ? serverCategory.hashCode() : 0);
        result = 31 * result + ownerId;
        return result;
    }
}
