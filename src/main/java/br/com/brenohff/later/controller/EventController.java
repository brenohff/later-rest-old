package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.brenohff.later.models.LTEvent;
import br.com.brenohff.later.service.EventService;

@RestController
@RequestMapping(value = "/events")
@CrossOrigin
public class EventController {

    @Autowired
    EventService service;

    @RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
    public void saveEvent(@RequestBody LTEvent event) {
        service.saveEvent(event);
    }

    @RequestMapping(value = "/getAll")
    public List<LTEvent> getAllEvents() {
        return service.getAllEvents();
    }

    @RequestMapping(value = "/getPublic")
    public ResponseEntity<List<LTEvent>> getPublic() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPublic());
    }

    @RequestMapping(value = "/getEventById")
    public LTEvent getEventById(@RequestParam(value = "event_id") Long event_id) {
        return service.getEventById(event_id);
    }

    @RequestMapping(value = "/getEventsByUser")
    public List<LTEvent> getEventsByUser(@RequestParam(value = "user_id") String user_id) {
        return service.getEventsByUser(user_id);
    }

    @RequestMapping(value = "/delete")
    public void delete() {
        List<LTEvent> events = service.getPublic();
        for (LTEvent event : events) {
            service.delete(event);
        }
    }

}
