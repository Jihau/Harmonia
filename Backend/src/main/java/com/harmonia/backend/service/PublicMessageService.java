package com.harmonia.backend.service;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.repository.PublicMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing public messages.
 *
 * @author Harmonia Team
 * @version 1.0
 */
@Service
public class PublicMessageService {

    /**
     * This annotation is used to automatically inject an instance of PublicMessageRepository
     * when this service is created, allowing us to use its methods to interact with the database.
     */
    @Autowired
    private PublicMessageRepository publicMessageRepository;

    /**
     * Constructor for PublicMessageService
     *
     * @param publicMessageRepository repository for public messages
     */
    public PublicMessageService(PublicMessageRepository publicMessageRepository) {
        this.publicMessageRepository = publicMessageRepository;
    }

    /**
     * Adds a public message to the database.
     *
     * @param pm the public message to be added
     * @return the added public message
     */
    public PublicMessage addPublicMessage(PublicMessage pm) {
        if (pm.getMessageText() == null || pm.getMessageText().trim().length() == 0) {
            return null;
        }
        // logic check the message before adding it
        return publicMessageRepository.save(pm);
    }

    /**
     * Lists all the public messages in the database
     * @return all the public messages in the database
     */
    public Iterable<PublicMessage> listPublicMessages() {
        return publicMessageRepository.findAll();
    }

    /**
     * Deletes a public message from the database by id
     * @param publicMessageId the id of the public message to be deleted
     */
    public void deletePublicMessage(Long publicMessageId) {
        publicMessageRepository.deleteById(publicMessageId);
        System.out.println("Message with ID: " + publicMessageId + " is deleted.");
    }

    /**
     * Edits the text of a public message in the database by id
     * @param pmessageId the id of the public message to be edited
     * @param newText the new text for the public message
     */
    public void editPublicMessage(Long pmessageId, String newText) {
        Optional<PublicMessage> optionalPublicMessage = publicMessageRepository.findById(pmessageId);
        if (optionalPublicMessage.isPresent()) {
            PublicMessage publicMessage = optionalPublicMessage.get();
            publicMessage.setMessageText(newText);
            publicMessageRepository.save(publicMessage);
        } else {
            throw new IllegalArgumentException("Message not found");
        }
    }
}