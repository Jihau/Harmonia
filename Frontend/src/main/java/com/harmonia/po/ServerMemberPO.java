package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * A class representing a member of a server in Harmonia.
 *
 * <p>This class includes the member's ID and username, as well as additional information
 * such as their email, profile icon, and bio. It also includes the server's ID and name,
 * as well as the member's nickname on the server and join date.</p>
 *
 * <p>Additionally, this class includes a {@link UserPO} object representing the member.</p>
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerMemberPO {
    /** The ID of the server this member is in. */
    int serverId;
    /** The ID of the member. */
    int memberId;
    /** The name of the server this member is in. */
    String serverName;
    /** The ID of the server member. */
    Long serverMemberId;
    /** The ID of the user associated with this server member. */
    Long userId;
    /** The username of the user associated with this server member. */
    String username;
    /** The email of the user associated with this server member. */
    String email;
    /** The URL of the profile icon of the user associated with this server member. */
    String profileIcon;
    /** The bio of the user associated with this server member. */
    String bio;
    /** A {@link UserPO} object representing the member. */
    UserPO member;
    /** The timestamp of when this member was created. */
    Date timestamp;
    /** The nickname of the member on the server. */
    String nickName;
    /** The date this member joined the server. */
    Date joinDate;
}