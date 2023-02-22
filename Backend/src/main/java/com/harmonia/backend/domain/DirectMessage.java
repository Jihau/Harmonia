package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "direct_message", schema = "harmoniadb")
public class DirectMessage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DmessageId")
    private Long dmessageId;
    @Basic
    @Column(name = "Message_text")
    @NotNull
    @JsonProperty("Message_text")
    private String messageText;
    @Basic
    @Column(name = "Timestamp")
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date timestamp;


    @JsonProperty("authorId")
    @Column(name = "AuthorId")
    private int authorId;


    @JsonProperty("recipientId")
    @Column(name = "RecipientId")
    private int recipientId;



    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getDmessageId() {
        return dmessageId;
    }

    public void setDmessageId(Long dmessageId) {
        this.dmessageId = dmessageId;
    }

    public int getAuthorId() { return authorId;}

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(int recipientId) {
        this.recipientId = recipientId;
    } @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectMessage that = (DirectMessage) o;
        if (!Objects.equals(dmessageId, that.dmessageId)) return false;
        return Objects.equals(messageText, that.messageText) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(authorId, that.authorId) &&
                Objects.equals(recipientId, that.recipientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dmessageId, messageText, timestamp, authorId, recipientId);
    }
}

