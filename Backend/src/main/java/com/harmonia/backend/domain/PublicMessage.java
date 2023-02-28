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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("ChannelId")
    @JoinColumn(name = "ChannelId", foreignKey = @ForeignKey(name = "Pmessages_byChannel_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    Channel channel;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("UserId")
    @JoinColumn(name = "AuthorId", foreignKey = @ForeignKey(name = "Author_ofPMessage_fk", value = ConstraintMode.CONSTRAINT), nullable = false)
    User Author;

    @Column(name = "ChannelId")
    private Long channelId;
    @Basic
    @Column(name = "AuthorId")
    private Long authorId;
    @Basic
    @Column(name = "Timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    @Generated(GenerationTime.INSERT)
    private Date timestamp;



}
