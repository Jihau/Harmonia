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
import java.sql.Timestamp;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ChannelId")
    @JoinColumn(name = "ChannelId")
    Channel channel;

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



}
