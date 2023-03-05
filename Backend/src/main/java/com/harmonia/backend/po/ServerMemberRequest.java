package com.harmonia.backend.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A class representing a request to add a member to a server.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerMemberRequest {

    /**
     * The ID of the member being added.
     */
    private Long memberId;

    /**
     * The ID of the server the member is being added to.
     */
    private Long serverId;

    /**
     * The nickname of the member in the server.
     */
    private String nickName;
}