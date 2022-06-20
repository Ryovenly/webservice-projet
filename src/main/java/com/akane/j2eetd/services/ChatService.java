package com.akane.j2eetd.services;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.Message;
import com.akane.j2eetd.repositories.CharactergameRepository;
import com.akane.j2eetd.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;

    public Message createOrUpdate(Message message) {
        return chatRepository.save(message);
    }

    public List<Message> getMessages() {
        return chatRepository.findAll();
    }

//    public List<Message> getMessagesFromRoom() {
//        return chatRepository.findBy();
//    }

    public Message getMessageId(Long id) {
        return chatRepository.findById(id).orElse(null);
    }
}
