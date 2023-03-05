package com.harmonia.po;

import com.harmonia.constants.HarmoniaConstants;
import com.harmonia.constants.HarmoniaData;
import com.harmonia.constants.HarmoniaMessagesConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DMessagePO implements Comparable<DMessagePO> {
    int dmessageId;
    int recipientId;
    int authorId;
    String messageText;
    String timestamp;

    @Override
    public int compareTo(DMessagePO other) {
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        if (HarmoniaConstants.LOGGED_USERS.getUserId() == this.authorId) {
            return HarmoniaMessagesConstants.LABEL_DIRECT_MESSAGES_AUTHOR + this.messageText;
        } else {
            return HarmoniaData.SELECTED_RECIPIENT.getUsername() + ": " + this.messageText;
        }
    }

}