package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class Represents a message to refresh the chat.
 * Author: Team Harmonia
 * Version: 2.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StreamMessageRequest {
    private String from;
    private String to;
    private String text;
}