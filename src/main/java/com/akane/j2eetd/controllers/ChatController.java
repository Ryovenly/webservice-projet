package com.akane.j2eetd.controllers;

import com.akane.j2eetd.entities.Charactergame;
import com.akane.j2eetd.entities.Message;
import com.akane.j2eetd.services.CharactergameService;
import com.akane.j2eetd.services.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Operation(summary = "Récupération d'un message à partir de son identifiant")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Message getMessage(@PathVariable(value = "id") Long id) {
        return chatService.getMessageId(id);
    }

    @Operation(summary = "Création ou mise à jour d'un message")
    @RequestMapping(method = RequestMethod.PUT)
    public Message addMessage(@RequestBody @Valid Message message) {
        return chatService.createOrUpdate(message);
    }

    @Operation(summary = "Récupération de toutes les messages")
    @RequestMapping(path = "_all", method = RequestMethod.GET)
    public List<Message> getMessages() {
        return chatService.getMessages();
    }

}
