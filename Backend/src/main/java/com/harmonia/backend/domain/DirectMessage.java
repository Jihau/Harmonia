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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "DirectMessage.listDMsByRecipientId", query = "FROM DirectMessage dm where dm.recipient.id = :recipientId"),
        @NamedQuery(name = "DirectMessage.listDMsByAuthorId", query = "FROM DirectMessage dm where dm.author.id = :authorId")
})
@Table(name = "direct_message", schema = "harmoniadb")
public class DirectMessage {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "AuthorId", foreignKey = @ForeignKey(name = "Author_ofDMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User author;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "RecipientId", foreignKey = @ForeignKey(name = "Recipient_ofDMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User recipient;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DmessageId")
    private Long dmessageId;
    @Column(name = "Message_text", nullable = false)
    @NotNull
    @JsonProperty("messageText")
    private String messageText;
    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date timestamp;
    @JsonProperty("authorId")
    @Column(name = "AuthorId" ,nullable = false)
    private Long authorId;
    @JsonProperty("recipientId")
    @Column(name = "RecipientId", nullable = false)
    private Long recipientId;
}

