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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name="ServerMember.listServersByMemberId", query = "FROM ServerMember serverMember where serverMember.memberId = : memberId"),
        @NamedQuery(name="ServerMember.listServerMembersByServerId", query = "FROM ServerMember serverMember where serverMember.serverId = : serverId")
})
@Table(name = "server_members", schema = "harmoniadb")

public class ServerMember {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ServerMemberId")
    private Long serverMemberId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ServerId")
    @JoinColumn(name = "ServerId", foreignKey = @ForeignKey(name = "Server_member_server_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Server server;

    @Transient
    String serverName;
    public String getServerName() {
        return server.getServerName();
    }

    @Column(name = "ServerId", nullable = false)
    Long serverId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "MemberId", foreignKey = @ForeignKey(name = "Server_member_user_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User member;

    @Column(name = "MemberId", nullable = false)
    Long memberId;

    @Column(name = "NickName")
    private String nickName;

    @Column(name = "JoinDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date joinDate;
}
