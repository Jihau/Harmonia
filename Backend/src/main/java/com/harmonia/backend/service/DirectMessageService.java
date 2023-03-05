package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.mappers.DirectMessagesMapper;
import com.harmonia.backend.po.DMessageRequest;
import com.harmonia.backend.po.DmessageResponse;
import com.harmonia.backend.repository.DirectMessageRepository;
import com.harmonia.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.micrometer.common.util.StringUtils.isBlank;

/**
 * Service class for managing direct messages.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class DirectMessageService {

    // This annotation is used to automatically inject an instance of DirectMessageRepository
    // when this service is created, allowing us to use its methods to interact with the database.
    @Autowired
    private DirectMessageRepository directMessageRepository;

    // This annotation is used to automatically inject an instance of UserRepository
    // when this service is created, allowing us to use its methods to retrieve user information.
    @Autowired
    private UserRepository userRepository;

    /**
     * Constructor for injecting dependencies.
     *
     * @param directMessageRepository the crud repository for direct messages
     */
    public DirectMessageService(DirectMessageRepository directMessageRepository){
        this.directMessageRepository = directMessageRepository;
    }

    /**
     * Adds a direct message to the database.
     * @param dmessageRequest the request object containing the message details
     * @return the created DirectMessage object
     * @throws Exception if the message is blank or the author/recipient are missing
     */
    public DirectMessage addDirectMessage(DMessageRequest dmessageRequest) throws Exception {

        // logic check the message before adding it
        if (dmessageRequest == null || isBlank(dmessageRequest.getMessageText())) {
            throw new Exception("You need to type something!");
        }
        User author = null;
        if (userRepository.findById(dmessageRequest.getAuthorId()).isPresent()) {
            author = userRepository.findById(dmessageRequest.getAuthorId()).get();
        } else {
            throw new Exception("You need an author!");
        }
        User recipient = null;
        if (userRepository.findById(dmessageRequest.getRecipientId()).isPresent()) {
            recipient = userRepository.findById(dmessageRequest.getRecipientId()).get();
        } else {
            throw new Exception("You need an recipient!");
        }
        DirectMessage directMessage = DirectMessagesMapper.createDirectMessageFromDirectMessageRequest(dmessageRequest, author, recipient);
        return directMessageRepository.save(directMessage);
    }

    /**
     * Returns all direct messages in the database.
     * @return an Iterable containing all direct messages
     */
    public Iterable<DmessageResponse> listMessages() {
        return StreamSupport.stream(directMessageRepository.findAll().spliterator(), false)
                .map(DmessageResponse::new).collect(Collectors.toList());
    }

    /**
     * Returns all direct messages in the database with the specified recipient ID.
     * @param recipientId the recipient ID to filter by
     * @return an Iterable containing all direct messages with the specified recipient ID
     */
    public Iterable<DmessageResponse> listDmessagesByRecipientId(Long recipientId){
        return directMessageRepository.listDMsByRecipientId(recipientId).stream().map(DmessageResponse::new).collect(Collectors.toList());
    }

    /**
     * Returns all direct messages in the database with the specified author ID.
     * @param authorId the author ID to filter by
     * @return an Iterable containing all direct messages with the specified author ID
     */
    public Iterable<DmessageResponse> listDmessagesByAuthorId(Long authorId){
        return directMessageRepository.listDMsByAuthorId(authorId).stream().map(DmessageResponse::new).collect(Collectors.toList());
    }

    /**
     * Deletes the specified direct message from the database.
     * @param dm the direct message to delete
     */
    public void deleteDirectMessage(DirectMessage dm){
        directMessageRepository.deleteById(dm.getDmessageId());
        System.out.println("Message with ID: " + dm.getDmessageId() + " is deleted.");
    }

    /**
     * Updates the text of the specified direct message.
     * @param directMessageId the ID of the direct message to update
     * @param newText the new text for the direct message
     * @throws IllegalArgumentException if the specified direct message is not found
     */
    public void editDirectMessage(Long directMessageId, String newText){
        Optional<DirectMessage> optionalDirectMessage = directMessageRepository.findById(directMessageId);
        if (optionalDirectMessage.isPresent()){
            DirectMessage directMessage = optionalDirectMessage.get();
            directMessage.setMessageText(newText);
            directMessageRepository.save(directMessage);
        }else {
            throw new IllegalArgumentException("Message not found");
        }
    }

    public List<DmessageResponse> listConversation(Long authorId, Long recipientId) {
        return directMessageRepository.listConversation(authorId, recipientId).stream().map(DmessageResponse::new).collect(Collectors.toList());
    }
}
