package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "channel", schema = "harmoniadb")
public class Channel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ChannelId")
    Long channelId;

    @Column(name = "ChannelName",nullable = false)
    @NotNull String channelName;

    @Column(name = "CreationDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",nullable = false)
    @NotNull
    @Generated(GenerationTime.INSERT)
    Date timestamp;

    @Column(name = "ChannelType", nullable = false)
    @NotNull String channelType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ServerId")
    @JoinColumn(name = "ServerId", foreignKey = @ForeignKey(name = "Channel_Server_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Server server;

    @Column(name = "ServerId",nullable = false)
    @NotNull
    Long serverId;


    @OneToMany(targetEntity = PublicMessage.class, mappedBy = "channel", fetch = FetchType.EAGER)
    Set<PublicMessage> publicMessages;
}
