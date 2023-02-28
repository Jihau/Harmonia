package com.harmonia.backend.po;

import com.harmonia.backend.domain.DirectMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DmessageResponse implements Serializable {
    Long dmessageId;
    String messageText;
    Date timestamp;
    Long recipientId;


    public DmessageResponse(DirectMessage dm){
        this.dmessageId = dm.getDmessageId();
        this.messageText = dm.getMessageText();
        this.timestamp = dm.getTimestamp();
        this.recipientId = dm.getRecipientId();
    }
}
