package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A class representing a response to a server request.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServerResponse implements Serializable {

    /**
     * The ID of the user who the servers belong to.
     */
    Long userId;

    /**
     * The servers.
     */
    String servers;
}


