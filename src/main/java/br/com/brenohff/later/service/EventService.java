package br.com.brenohff.later.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brenohff.later.models.LTEvent;
import br.com.brenohff.later.repository.EventRepository;
import br.com.brenohff.later.repository.UserRepository;

@Service
public class EventService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	public void saveEvent(LTEvent event) {		
		eventRepository.save(event);
	}
	
	public List<LTEvent> getAllEvents(){
		return eventRepository.findAll();
	}
	
	public List<LTEvent> getEventsByUser(Long user_id){
		return eventRepository.getEventsByUser(user_id);
	}

}
