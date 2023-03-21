package com.harmonia.backend.po;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.PublicMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * A class representing a response to a public message request.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PmessageResponse implements Serializable {

    /**
     * The ID of the public message.
     */
    Long pmessageId;

    /**
     * The text of the public message.
     */
    String messageText;

    /**
     * The timestamp of when the public message was sent.
     */
    Date timestamp;

    /**
     * The ID of the channel of the public message was sent in.
     */
    Long channelId;

    /**
     * The ID of the author of the public message.
     */
    Long authorId;

    /**
     * Constructs a PmessageResponse object from a DirectMessage object.
     *
     * @param pm the PublicMessage object
     */
    public PmessageResponse(PublicMessage pm) {
        this.pmessageId = pm.getPmessageId();
        this.messageText = pm.getMessageText();
        this.timestamp = pm.getTimestamp();
        this.channelId = pm.getChannelId();
        this.authorId = pm.getAuthorId();
    }
}