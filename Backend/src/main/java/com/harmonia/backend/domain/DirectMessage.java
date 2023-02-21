package com.harmonia.backend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import javax.validation.constraints.NotNull;
import java.sql.Date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DirectMessage that = (DirectMessage) o;

        if (dmessageId != that.dmessageId) return false;
        if (messageText != null ? !messageText.equals(that.messageText) : that.messageText != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = messageText != null ? messageText.hashCode() : 0;
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + Long.hashCode(dmessageId);
        return result;
    }
}
