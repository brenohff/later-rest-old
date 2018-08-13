package br.com.brenohff.later.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import br.com.brenohff.later.model.LTChat;
import br.com.brenohff.later.service.ChatService;

@Controller
@CrossOrigin
public class ChatController {

    @Autowired
    ChatService chatService;

    @MessageMapping("/event/{eventId}/addUser")
    @SendTo("/topic/event/{eventId}")
    public LTChat addUser(@DestinationVariable String eventId, @Payload LTChat chatMessage,
                          SimpMessageHeaderAccessor headerAccessor) {
        chatMessage.setEventId(eventId);
        headerAccessor.getSessionAttributes().put("chatMessage", chatMessage);
        return chatMessage;
    }

    @MessageMapping("/event/{eventId}/sendMessage")
    @SendTo("/topic/event/{eventId}")
    public LTChat sendMessage(@DestinationVariable String eventId, @Payload LTChat chatMessage) {
        chatMessage.setEventId(eventId);
        return chatService.saveChat(chatMessage);
    }

    @GetMapping(value = "/chat/getAll")
    public ResponseEntity<List<LTChat>> buscaChat() {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.getAll());
    }

    @GetMapping(value = "/chat/getChatByEventId")
    public ResponseEntity<List<LTChat>> getChatByEventId(@RequestParam("eventId") String eventId) {
        return ResponseEntity.status(HttpStatus.OK).body(chatService.getChatByEventId(eventId));
    }
}