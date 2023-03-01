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
    private String messageText;
    private Long authorId;
    private Long recipientId;
}
