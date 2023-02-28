package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
import com.harmonia.backend.po.DmessageResponse;
import com.harmonia.backend.repository.DirectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DirectMessageService {
    @Autowired
    private DirectMessageRepository directMessageRepository;

    public DirectMessageService(DirectMessageRepository directMessageRepository){
        this.directMessageRepository = directMessageRepository;
    }

    public DmessageResponse addDirectMessage(DirectMessage dm) {

        // logic check the message before adding it
        if (dm.getMessageText() == null || dm.getMessageText().trim().length() == 0) {
            return null;
        }
        return new DmessageResponse(directMessageRepository.save(dm));
    }

    public Iterable<DmessageResponse> listMessages() {
        return StreamSupport.stream(directMessageRepository.findAll().spliterator(), false)
                .map(DmessageResponse::new).collect(Collectors.toList());
    }

    //Get DMs by recipient id
    public Iterable<DmessageResponse> listDmessagesByRecipientId(Long recipientId){
        return directMessageRepository.listDMsByRecipientId(recipientId).stream().map(DmessageResponse::new).collect(Collectors.toList());
    }

    //Get DMs by author id
    public Iterable<DmessageResponse> listDmessagesByAuthorId(Long authorId){
        return directMessageRepository.listDMsByAuthorId(authorId).stream().map(DmessageResponse::new).collect(Collectors.toList());
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
