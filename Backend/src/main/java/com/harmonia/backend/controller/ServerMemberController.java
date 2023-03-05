package com.harmonia.backend.controller;

import com.harmonia.backend.domain.ServerMember;
import com.harmonia.backend.po.ServerMemberRequest;
import com.harmonia.backend.service.ServerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a REST controller for handling server members.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("member")
public class ServerMemberController {
    @Autowired
    private ServerMemberService serverMemberService;

    /**
     * Adds a new server member to the specified server.
     *
     * @param serverMemberRequest the ServerMemberRequest object containing the member and server ids.
     * @return a ResponseEntity with the newly added ServerMember object, or a HttpStatus.BAD_REQUEST if the addition failed.
     */
    @PostMapping
    public ResponseEntity<ServerMember> addServerMember(@RequestBody ServerMemberRequest serverMemberRequest) {
        try {
            return new ResponseEntity<>(serverMemberService.addMemberToServer(serverMemberRequest), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Lists all servers that a given member is a part of.
     *
     * @param memberId the id of the member to filter by.
     * @return an iterable of ServerMember objects.
     */
    @GetMapping("memberId/{memberId}")
    @CrossOrigin
    public Iterable<ServerMember> listServersByMemberId(@PathVariable("memberId") Long memberId) {
        return serverMemberService.listServersByMemberId(memberId);
    }

    /**
     * Lists all members that are part of the specified server.
     *
     * @param serverId the id of the server to filter by.
     * @return an iterable of ServerMember objects.
     */
    @GetMapping("serverId/{serverId}")
    @CrossOrigin
    public Iterable<ServerMember> listServerMembersByServerId(@PathVariable("serverId") Long serverId) {
        return serverMemberService.listServerMembersByServerId(serverId);
    }
}
