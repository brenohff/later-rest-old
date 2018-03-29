package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTEvent;
import br.com.brenohff.later.service.EventService;

@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	EventService service;

	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public void saveEvent(@RequestBody LTEvent event) {
		service.saveEvent(event);
	}
	
	@RequestMapping(value = "/getAllEvents")
	public List<LTEvent> getAllEvents() {
		return service.getAllEvents();
	}
	
	@RequestMapping(value = "/getEventsByUser")
	public List<LTEvent> getEventsByUser(@RequestParam(value="user_id") Long user_id) {
		return service.getEventsByUser(user_id);
	}

}
