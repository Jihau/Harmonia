package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * A class representing a channel in a server.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelPO {

    /**
     * The ID of the channel.
     */
    int channelId;

    /**
     * The ID of the server that the channel belongs to.
     */
    int serverId;

    /**
     * The name of the channel.
     */
    String channelName;

    /**
     * The timestamp of when the channel was created.
     */
    Date timestamp;

    /**
     * The type of the channel.
     */
    String channelType;

    /**
     * The set of public messages in the channel.
     */
    Set<PublicMessagePO> publicMessages;

    /**
     * Returns a string representation of the channel in the format of "#channelName".
     *
     * @return a string representation of the channel
     */
    @Override
    public String toString() {
        return "#" + this.channelName;
    }
}