package com.harmonia.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServerMemberPO {
    int serverId;
    int memberId;
    String serverName;
    Long serverMemberId;
    Long userId;
    String username;
    String email;
    String profileIcon;
    String bio;
    UserPO member;
    Date timestamp;
    String nickName;
    Date joinDate;

}