package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicMessagePO {
    String messageText;
    Long pmessageId;
    Long authorId;
    Long channelId;
    Date timestamp;
}
