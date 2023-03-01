package com.harmonia.backend.controller;

import com.harmonia.backend.domain.ServerMember;
import com.harmonia.backend.po.ServerMemberRequest;
import com.harmonia.backend.service.ServerMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class ServerMemberController {
    @Autowired
    private ServerMemberService serverMemberService;

    @PostMapping
    public ResponseEntity<ServerMember> addServerMember(@RequestBody ServerMemberRequest serverMemberRequest) {
        try {
            return new ResponseEntity<>(serverMemberService.addMemberToServer(serverMemberRequest), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
