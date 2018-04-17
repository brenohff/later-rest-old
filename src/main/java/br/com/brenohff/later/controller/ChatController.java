package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.brenohff.later.models.LTChat;
import br.com.brenohff.later.service.ChatService;

@Controller
public class ChatController {

	@Autowired
	ChatService service;

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public LTChat sendMessage(@Payload LTChat chatMessage) {
		service.salvarMensagem(chatMessage);
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public LTChat addUser(@Payload LTChat chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@Autowired
	private ChatService chatService;

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ResponseEntity<List<LTChat>> buscaChat() {
		return ResponseEntity.status(HttpStatus.OK).body(chatService.listarMensagens());
	}

}
