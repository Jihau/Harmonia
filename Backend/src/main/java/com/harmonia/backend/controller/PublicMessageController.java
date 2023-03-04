package com.harmonia.backend.controller;


import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.service.PublicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pmessage")
public class PublicMessageController {

    @Autowired
    private PublicMessageService publicMessageService;

    public PublicMessageController(PublicMessageService pmService) {
        this.publicMessageService = pmService;
    }

    @GetMapping
    @CrossOrigin
    public Iterable<PublicMessage> listMessages(@RequestParam(name = "pmessageId", required = false) String dmId) {
        return publicMessageService.listPublicMessages();
    }

    @PostMapping
    @CrossOrigin
    public PublicMessage sendPMessage(@RequestBody PublicMessage publicMessage) {
        return publicMessageService.addPublicMessage(publicMessage);
    }

    @DeleteMapping("pmessageId/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> deletePMessage(@PathVariable("messageId") Long publicMessageId) {
        System.out.println("C: the message with id : " + publicMessageId + " is deleted");
        publicMessageService.deletePublicMessage(publicMessageId);
        return new ResponseEntity<>("Message has been deleted", HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> editMessage(@PathVariable Long messageId, @RequestBody PublicMessage publicMessage) {
        publicMessageService.editPublicMessage(messageId, publicMessage.getMessageText());
        return ResponseEntity.ok("Message has been edited");
    }
}
