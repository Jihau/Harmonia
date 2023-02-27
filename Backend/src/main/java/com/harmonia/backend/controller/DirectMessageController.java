package com.harmonia.backend.controller;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.service.DirectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("dmessage")
public class DirectMessageController {

    @Autowired
    private DirectMessageService directMessageService;

    public DirectMessageController(DirectMessageService dmService){
        this.directMessageService = dmService;
    }

    @GetMapping
    @CrossOrigin
    public Iterable<DirectMessage> listMessages(@RequestParam(name="dmessageId", required = false) String dmId){
      return directMessageService.listMessages();
    }

    @GetMapping("{recipientId}")
    @CrossOrigin
    public Iterable<DirectMessage> listDMsByRecipientId(@PathVariable("recipientId") Long recipientId){
        return directMessageService.listDmessagesByRecipientId(recipientId);
    }

    @PostMapping
    @CrossOrigin
    public DirectMessage sendDMessage(@RequestBody DirectMessage directMessage){
        return directMessageService.addDirectMessage(directMessage);
    }

    @DeleteMapping
    @CrossOrigin
    public ResponseEntity<String> deleteDMessage(@RequestBody DirectMessage directMessage) {

        System.out.println("C: the message with id : " + directMessage.getDmessageId() + " is deleted");
        directMessageService.deleteDirectMessage(directMessage);

        return new ResponseEntity<>("Message has been deleted", HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> editMessage(@PathVariable Long messageId, @RequestBody DirectMessage directMessage){
       directMessageService.editDirectMessage(messageId, directMessage.getMessageText());
       return ResponseEntity.ok("Message has been edited");
    }
}
