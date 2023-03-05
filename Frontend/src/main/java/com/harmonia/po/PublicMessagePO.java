package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * A class representing a public message posted in a channel.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicMessagePO {

    /**
     * The text of the message.
     */
    String messageText;

    /**
     * The unique identifier of the public message.
     */
    Long pmessageId;

    /**
     * The unique identifier of the user who authored the message.
     */
    Long authorId;

    /**
     * The unique identifier of the channel where the message was sent.
     */
    Long channelId;

    /**
     * The timestamp when the message was sent.
     */
    Date timestamp;
}
