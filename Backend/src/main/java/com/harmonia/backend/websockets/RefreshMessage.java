package com.harmonia.backend.websockets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class Represents a message to refresh the chat.
 * @version  2.0
 * @author  Team Harmonia
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshMessage {
    private String from;
    private String text;
}
