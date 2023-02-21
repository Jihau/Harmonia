package com.harmonia.backend.service;

import com.harmonia.backend.domain.DirectMessage;
import com.harmonia.backend.repository.DirectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
