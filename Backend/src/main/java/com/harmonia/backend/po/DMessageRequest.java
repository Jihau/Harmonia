package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DMessageRequest {
    /**
     * {
     *     "Message_text": "Hello, I am a message with an author and recipient test",
     *     "authorId": 2,
     *     "recipientId":1
     * }
     */
    private String messageText;
    private Long authorId;
    private Long recipientId;
}
