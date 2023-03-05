package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * DirectMessage entity represents a direct message sent between two users.
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
        // Retrieve a list of Direct Messages by the recipient's User ID
        @NamedQuery(name = "DirectMessage.listDMsByRecipientId", query = "FROM DirectMessage dm where dm.recipient.id = :recipientId"),
        // Retrieve a list of Direct Messages by the author's User ID
        @NamedQuery(name = "DirectMessage.listDMsByAuthorId", query = "FROM DirectMessage dm where dm.author.id = :authorId"),
        @NamedQuery(name = "DirectMessage.listConversation", query = "FROM DirectMessage dm where (dm.author.id = :authorId AND dm.recipient.id = :recipientId) OR (dm.author.id = :recipientId AND dm.recipient.id = :authorId) ORDER BY dm.dmessageId")})
@Table(name = "direct_message", schema = "harmoniadb")
public class DirectMessage {

    /**
     * The user who sent the message.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "AuthorId", foreignKey = @ForeignKey(name = "Author_ofDMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User author;

    /**
     * The user who received the message.
     */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "RecipientId", foreignKey = @ForeignKey(name = "Recipient_ofDMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User recipient;

    /**
     * The unique identifier for the direct message.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DmessageId")
    private Long dmessageId;

    /**
     * The text of the message.
     */
    @Column(name = "Message_text", nullable = false)
    @NotNull
    @JsonProperty("messageText")
    private String messageText;

    /**
     * The time the message was sent.
     */
    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date timestamp;

    /**
     * The ID of the user who sent the message.
     */
    @JsonProperty("authorId")
    @Column(name = "AuthorId", nullable = false)
    private Long authorId;

    /**
     * The ID of the user who received the message.
     */
    @JsonProperty("recipientId")
    @Column(name = "RecipientId", nullable = false)
    private Long recipientId;
}

