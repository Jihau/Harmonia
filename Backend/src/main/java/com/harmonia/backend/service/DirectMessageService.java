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

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
public class DirectMessageService {
    @Autowired
    private DirectMessageRepository directMessageRepository;

    @Autowired
    private UserRepository userRepository;

    public DirectMessageService(DirectMessageRepository directMessageRepository){
        this.directMessageRepository = directMessageRepository;
    }

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
