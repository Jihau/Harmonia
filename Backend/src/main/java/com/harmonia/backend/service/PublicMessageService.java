package com.harmonia.backend.service;

import com.harmonia.backend.domain.PublicMessage;
import com.harmonia.backend.repository.PublicMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublicMessageService {
    @Autowired
    private PublicMessageRepository publicMessageRepository;

    public PublicMessageService(PublicMessageRepository publicMessageRepository){
        this.publicMessageRepository = publicMessageRepository;
    }

    public PublicMessage addPublicMessage(PublicMessage pm) {
        if (pm.getMessageText() == null || pm.getMessageText().trim().length() == 0) {
            return null;
        }
        // logic check the message before adding it
        return publicMessageRepository.save(pm);
    }

    public Iterable<PublicMessage> listPublicMessages() {
        return publicMessageRepository.findAll();
    }

    public void deletePublicMessage(PublicMessage pm){
        publicMessageRepository.deleteById(pm.getPMessageId());
        System.out.println("Message with ID: " + pm.getPMessageId() + " is deleted.");
    }

    public void editPublicMessage(Long pmessageId, String newText){
        Optional<PublicMessage> optionalPublicMessage = publicMessageRepository.findById(pmessageId);
        if (optionalPublicMessage.isPresent()){
            PublicMessage publicMessage = optionalPublicMessage.get();
            publicMessage.setMessageText(newText);
            publicMessageRepository.save(publicMessage);
        }else {
            throw new IllegalArgumentException("Message not found");
        }
    }
}