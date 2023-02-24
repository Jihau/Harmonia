package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "channel", schema = "harmoniadb")
public class Channel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ChannelId")
    Long channelId;

    @Column(name = "ChannelName")
    @NotNull
    String channelName;

    @Column(name = "Timestamp")
    @NotNull
    @Generated(GenerationTime.INSERT)
    Date timestamp;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ServerId")
    @JoinColumn(name = "ServerId")
    Server server;

    @Column(name = "ServerId")
    @NotNull
    Long serverId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("UserId")
    @JoinColumn(name = "UserId")
    User user;

    @Column(name = "UserId")
    @NotNull
    Long userId;

}
