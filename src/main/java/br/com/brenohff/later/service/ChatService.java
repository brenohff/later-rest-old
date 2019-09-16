package br.com.brenohff.later.service;

import br.com.brenohff.later.model.LTChat;
import br.com.brenohff.later.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public LTChat saveChat(LTChat message) {
        message.setDtPost(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime());
        chatRepository.save(message);
        return message;
    }

    public List<LTChat> getAll() {
        return chatRepository.findAll();
    }

    public List<LTChat> getChatByEventId(String eventId) {
        return chatRepository.getChatByEventId(eventId);
    }

}
