package br.com.brenohff.later.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenohff.later.models.LTChat;
import br.com.brenohff.later.repository.ChatRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;

	public void saveChat(LTChat message) {
		message.setDtPost(new Date());
		chatRepository.save(message);
	}

	public List<LTChat> getAll() {
		return chatRepository.findAll();
	}

	public List<LTChat> getChatByEventId(String eventId) {
		return chatRepository.getChatByEventId(eventId);
	}

}
