package br.com.brenohff.later.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenohff.later.models.LTChat;
import br.com.brenohff.later.repository.ChatRepository;

@Service
public class ChatService {
	
	@Autowired
	private ChatRepository chatRepository;
	
	public void salvarMensagem(LTChat message) {
		chatRepository.save(message);
	}
	
	public List<LTChat> listarMensagens() {
		return chatRepository.findAll();
	}

}