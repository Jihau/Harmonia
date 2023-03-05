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
 * PublicMessage entity represents a public message sent in a channel.
 *
 * @author Harmonia team
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "public_message", schema = "harmoniadb")
public class PublicMessage {
    /**
     * The channel in which the message was sent.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ChannelId")
    @JoinColumn(name = "ChannelId", foreignKey = @ForeignKey(name = "Pmessages_byChannel_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Channel channel;
    /**
     * The user who sent the message.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "AuthorId", foreignKey = @ForeignKey(name = "Author_ofPMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User Author;
    /**
     * The text content of the message.
     */
    @Basic
    @NotNull
    @Column(name = "Message_text", nullable = false)
    private String messageText;
    /**
     * The ID of the public message.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PmessageId")
    private Long pmessageId;
    /**
     * The ID of the channel in which the message was sent.
     */
    @Column(name = "ChannelId", nullable = false)
    private Long channelId;

    /**
     * The ID of the user who sent the message.
     */
    @Basic
    @Column(name = "AuthorId", nullable = false)
    private Long authorId;

    /**
     * The time and date of when the message was sent.
     */
    @Basic
    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date timestamp;


}
