package com.harmonia.po;

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

    public String prettyString() {
        return timestamp + ": " + messageText;
    }
}