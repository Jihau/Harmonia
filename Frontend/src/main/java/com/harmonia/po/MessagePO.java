package com.harmonia.po;

import com.harmonia.constants.HarmoniaMessagesConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessagePO implements Comparable<MessagePO> {
    int dmessageId;
    int recipientId;
    int authorId;
    String messageText;
    String timestamp;

    @Override
    public int compareTo(MessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        return this.messageText;
    }

    public String getMessageWithAuthorLabelText(int authorId, String recipientName) {
        if (authorId == this.authorId) {
            return HarmoniaMessagesConstants.LABEL_DIRECT_MESSAGES_AUTHOR + this.prettyString();
        } else {
            return recipientName + ": " + this.prettyString();
        }

    }

    public String prettyString() {
        return timestamp + ": " + messageText;
    }
}