package com.harmonia.backend.controller;


import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.service.PublicMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class represents a REST controller for handling public messages.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("pmessage")
public class PublicMessageController {

    /**
     * Autowires an instance of PublicMessageService to be used by this controller.
     */
    @Autowired
    private PublicMessageService publicMessageService;

    /**
     * Constructor that sets the PublicMessageService for this controller.
     *
     * @param pmService the PublicMessageService instance to be used by this controller.
     */
    public PublicMessageController(PublicMessageService pmService) {
        this.publicMessageService = pmService;
    }

    /**
     * Returns a list of all public messages.
     *
     * @return an iterable of PublicMessage objects.
     */
    @GetMapping
    @CrossOrigin
    public Iterable<PublicMessage> listMessages() {
        return publicMessageService.listPublicMessages();
    }

    /**
     * Creates a new public message.
     *
     * @param publicMessage the PublicMessage object to be created.
     * @return the newly created PublicMessage object.
     */
    @PostMapping
    @CrossOrigin
    public PublicMessage sendPMessage(@RequestBody PublicMessage publicMessage) {
        return publicMessageService.addPublicMessage(publicMessage);
    }

    /**
     * Deletes a public message.
     *
     * @param publicMessageId the id of the public message to be deleted.
     * @return a ResponseEntity with a message indicating success or failure of the deletion.
     */
    @DeleteMapping("messageId/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> deletePMessage(@PathVariable("messageId") Long publicMessageId) {
        System.out.println("C: the message with id : " + publicMessageId + " is deleted");
        publicMessageService.deletePublicMessage(publicMessageId);
        return new ResponseEntity<>("Message has been deleted", HttpStatus.OK);
    }

    /**
     * Updates an existing public message.
     *
     * @param messageId     the id of the public message to be updated.
     * @param publicMessage the PublicMessage object containing the updated message text.
     * @return a ResponseEntity with a message indicating success or failure of the update.
     */
    @PutMapping("messageId/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> editMessage(@PathVariable Long messageId, @RequestBody PublicMessage publicMessage) {
        publicMessageService.editPublicMessage(messageId, publicMessage.getMessageText());
        return ResponseEntity.ok("Message has been edited");
    }
}
