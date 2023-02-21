package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.repository.DirectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectMessageService {
    @Autowired
    private DirectMessageRepository directMessageRepository;

    public DirectMessageService(DirectMessageRepository directMessageRepository){
        this.directMessageRepository = directMessageRepository;
    }

    public DirectMessage addDirectMessage(DirectMessage dm) {
        if (dm.getMessageText() == null || dm.getMessageText().trim().length() == 0) {
            return null;
        }
        // logic check the message before adding it
        return directMessageRepository.save(dm);
    }

    public Iterable<DirectMessage> listMessages() {
        return directMessageRepository.findAll();
    }

    public void deleteDirectMessage(DirectMessage dm){
        directMessageRepository.deleteById(dm.getDmessageId());
        System.out.println("Message with ID: " + dm.getDmessageId() + " is deleted.");
    }

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
}
