package com.harmonia.backend.po;

import com.harmonia.backend.domain.DirectMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * A class representing a response to a direct message request.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DmessageResponse implements Serializable {

    /**
     * The ID of the direct message.
     */
    Long dmessageId;

    /**
     * The text of the direct message.
     */
    String messageText;

    /**
     * The timestamp of when the direct message was sent.
     */
    Date timestamp;

    /**
     * The ID of the recipient of the direct message.
     */
    Long recipientId;

    /**
     * The ID of the author of the direct message.
     */
    Long authorId;

    /**
     * Constructs a DmessageResponse object from a DirectMessage object.
     *
     * @param dm the DirectMessage object
     */
    public DmessageResponse(DirectMessage dm) {
        this.dmessageId = dm.getDmessageId();
        this.messageText = dm.getMessageText();
        this.timestamp = dm.getTimestamp();
        this.recipientId = dm.getRecipientId();
        this.authorId = dm.getAuthorId();
    }
}