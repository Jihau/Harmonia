package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPO {
    int channelId;
    int serverId;
    String channelName;
    Date timestamp;
    String channelType;
    Set<PublicMessagePO> publicMessages;

    @Override
    public String toString() {
        return "#" + this.channelName;
    }
}