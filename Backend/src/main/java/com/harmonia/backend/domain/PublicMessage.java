package com.harmonia.backend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "public_message", schema = "harmoniadb")
public class PublicMessage {
    @Basic
    @NotNull
    @Column(name = "Message_text")
    private String messageText;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PmessageId")
    private Long pmessageId;
    @Basic
    @Column(name = "ChannelId")
    private int channelId;
    @Basic
    @Column(name = "AuthorId")
    private int authorId;
    @Basic
    @Column(name = "Timestamp")
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Timestamp timestamp;

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getPMessageId() {
        return pmessageId;
    }

    public Long setPublicMessageId(int publicMessageId) {
        return pmessageId;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int userId) {
        this.authorId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicMessage that = (PublicMessage) o;

        if (pmessageId != that.pmessageId) return false;
        if (channelId != that.channelId) return false;
        if (authorId != that.authorId) return false;
        if (messageText != null ? !messageText.equals(that.messageText) : that.messageText != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageText != null ? messageText.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + Long.hashCode(pmessageId);
        result = 31 * result + channelId;
        result = 31 * result + authorId;
        return result;
    }
}
