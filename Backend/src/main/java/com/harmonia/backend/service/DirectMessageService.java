package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.domain.User;
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


    /*
    public DirectMessage addDirectMessage(String messageText, User author, User recipient) {
    if (messageText == null || messageText.trim().length() == 0) {
        return null;
    }
    // Create a new DirectMessage object and set the fields
    DirectMessage dm = new DirectMessage();
    dm.setMessageText(messageText);
    dm.setAuthor(author);
    dm.setRecipient(recipient);
    // Save the DirectMessage object to the repository
    return directMessageRepository.save(dm);
}
     */
    public DirectMessage addDirectMessage(DirectMessage dm, Long authorId, Long recipientId) {

        // logic check the message before adding it
        if (dm.getMessageText() == null || dm.getMessageText().trim().length() == 0) {
            return null;
        }

        int authorIdInt = authorId.intValue();
        int recipientIdInt = recipientId.intValue();
        dm.setAuthorId(authorIdInt);
        dm.setRecipientId(recipientIdInt);

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
