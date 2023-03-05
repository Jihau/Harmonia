package com.harmonia.po;

import com.harmonia.constants.HarmoniaMessagesConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class representing a message exchanged between two users.
 * Implements Comparable to allow sorting by timestamp.
 * Provides methods to create labels and pretty string representation.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagePO implements Comparable<MessagePO> {
    /**
     * The ID of the message.
     */
    private int dmessageId;

    /**
     * The ID of the recipient of the message.
     */
    private int recipientId;

    /**
     * The ID of the author of the message.
     */
    private int authorId;

    /**
     * The content of the message.
     */
    private String messageText;

    /**
     * The timestamp of the message.
     */
    private String timestamp;

    /**
     * Compares this message to another by their timestamps.
     * @param other the other message to compare to
     * @return a negative integer, zero, or a positive integer as this message is less than, equal to, or greater than the specified message.
     */
    @Override
    public int compareTo(MessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    /**
     * Returns a string representation of the message content.
     * @return the message content as a string
     */
    @Override
    public String toString() {
        return this.messageText;
    }

    /**
     * Returns a label for the message with the author's name, or just a label for the recipient.
     * @param authorId the ID of the message author
     * @param recipientName the name of the message recipient
     * @return a string label for the message
     */
    public String getMessageWithAuthorLabelText(int authorId, String recipientName) {
        if (authorId == this.authorId) {
            return HarmoniaMessagesConstants.LABEL_DIRECT_MESSAGES_AUTHOR + this.prettyString();
        } else {
            return recipientName + ": " + this.prettyString();
        }
    }

    /**
     * Returns a pretty string representation of the message with its timestamp.
     * @return a string representation of the message with timestamp
     */
    public String prettyString() {
        return timestamp + ": " + messageText;
    }
}