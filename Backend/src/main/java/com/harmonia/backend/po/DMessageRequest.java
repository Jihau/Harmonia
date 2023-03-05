package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class representing a request to create a direct message.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DMessageRequest {

    /**
     * The text of the direct message.
     */
    private String messageText;

    /**
     * The ID of the author of the direct message.
     */
    private Long authorId;

    /**
     * The ID of the recipient of the direct message.
     */
    private Long recipientId;
}
