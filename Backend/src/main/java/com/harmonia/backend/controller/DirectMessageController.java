package com.harmonia.backend.controller;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.po.DMessageRequest;
import com.harmonia.backend.po.DmessageResponse;
import com.harmonia.backend.service.DirectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This class is a REST controller for DirectMessage API endpoints.
 * It handles GET requests for listing direct messages and filtering them by recipient and author ID.
 *
 * @author Harmonia team
 * @version 1.0
 */
@RestController
@RequestMapping("dmessage")
public class DirectMessageController {

    /**
     * Service that provides direct message operations.
     */
    @Autowired
    private DirectMessageService directMessageService;

    /**
     * Constructs a DirectMessageController instance with a DirectMessageService instance.
     *
     * @param dmService the DirectMessageService instance used to handle direct message operations
     */
    public DirectMessageController(DirectMessageService dmService) {
        this.directMessageService = dmService;
    }

    /**
     * Handles GET requests to list all direct messages or filter them by direct message ID.
     *
     * @param dmId the ID of the direct message to filter by (optional)
     * @return an iterable collection of DmessageResponse objects representing the direct messages that match the filter
     */
    @GetMapping
    @CrossOrigin
    public Iterable<DmessageResponse> listMessages(@RequestParam(name = "dmessageId", required = false) String dmId) {
        return directMessageService.listMessages();
    }

    /**
     * Handles GET requests to list all direct messages that have the given recipient ID.
     *
     * @param recipientId the ID of the recipient to filter by
     * @return an iterable collection of DmessageResponse objects representing the direct messages that match the filter
     */

    @GetMapping("recipientId/{recipientId}")
    @CrossOrigin
    public Iterable<DmessageResponse> listDMsByRecipientId(@PathVariable("recipientId") Long recipientId) {
        return directMessageService.listDmessagesByRecipientId(recipientId);
    }

    /**
     * Handles GET requests to list all direct messages that have the given author ID.
     *
     * @param authorId the ID of the author to filter by
     * @return an iterable collection of DmessageResponse objects representing the direct messages that match the filter
     */
    @GetMapping("authorId/{authorId}")
    @CrossOrigin
    public Iterable<DmessageResponse> listDMsByAuthorId(@PathVariable("authorId") Long authorId) {
        return directMessageService.listDmessagesByAuthorId(authorId);
    }

    /**
     * Handles POST requests to send a direct message.
     *
     * @param directMessageRequest a DMessageRequest object representing the direct message to send
     * @return a ResponseEntity containing the DirectMessage object that was sent, or a BAD_REQUEST status if an error occurred
     */

    @PostMapping
    public ResponseEntity<DirectMessage> sendDMessage(@RequestBody DMessageRequest directMessageRequest) {
        try {
            return new ResponseEntity<>(directMessageService.addDirectMessage(directMessageRequest), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handles DELETE requests to delete a direct message.
     *
     * @param directMessage a DirectMessage object representing the direct message to delete
     * @return a ResponseEntity containing a message indicating that the direct message was deleted, or a BAD_REQUEST status if an error occurred
     */
    @DeleteMapping
    @CrossOrigin
    public ResponseEntity<String> deleteDMessage(@RequestBody DirectMessage directMessage) {

        System.out.println("C: the message with id : " + directMessage.getDmessageId() + " is deleted");
        directMessageService.deleteDirectMessage(directMessage);

        return new ResponseEntity<>("Message has been deleted", HttpStatus.OK);
    }

    /**
     * Edit a direct message.
     *
     * @param messageId     the ID of the direct message to edit
     * @param directMessage the DirectMessage object with the updated information
     * @return a ResponseEntity containing a message indicating success and a HTTP status code
     */
    @PutMapping("messageId/{messageId}")
    @CrossOrigin
    public ResponseEntity<String> editMessage(@PathVariable Long messageId, @RequestBody DirectMessage directMessage) {
        directMessageService.editDirectMessage(messageId, directMessage.getMessageText());
        return ResponseEntity.ok("Message has been edited");
    }

    @GetMapping("authorId/{authorId}/recipientId/{recipientId}")
    @CrossOrigin
    public Iterable<DmessageResponse> listDMsByRecipientId(@PathVariable("authorId") Long authorId, @PathVariable("recipientId") Long recipientId) {
        return directMessageService.listConversation(authorId, recipientId);
    }
}
