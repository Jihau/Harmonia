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

/**
 * Channel entity representing a communication channel within a server.
 *
 * @author Harmonia team
 * @version 1.0
 */
@Getter
@Setter
@Entity
@Table(name = "channel", schema = "harmoniadb")
public class Channel {

    /**
     * The unique identifier for the channel.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ChannelId")
    Long channelId;

    /**
     * The name of the channel.
     */
    @Column(name = "ChannelName", nullable = false)
    @NotNull String channelName;

    /**
     * The date and time the channel was created.
     */
    @Column(name = "CreationDate", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @NotNull
    @Generated(GenerationTime.INSERT)
    Date timestamp;

    /**
     * The type of channel (e.g. text, voice).
     */
    @Column(name = "ChannelType", nullable = false)
    @NotNull String channelType;

    /**
     * The server to which the channel belongs.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ServerId")
    @JoinColumn(name = "ServerId", foreignKey = @ForeignKey(name = "Channel_Server_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Server server;

    /**
     * The ID of the server to which the channel belongs.
     */
    @Column(name = "ServerId", nullable = false)
    @NotNull Long serverId;

    /**
     * The public messages that have been sent in the channel.
     */
    @OneToMany(targetEntity = PublicMessage.class, mappedBy = "channel", fetch = FetchType.EAGER)
    Set<PublicMessage> publicMessages;
}
