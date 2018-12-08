package br.com.brenohff.later.api;

import br.com.brenohff.later.model.LTEvent;
import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/userEvent")
@CrossOrigin
public class UserEventController {

    @Autowired
    UserEventService service;

    @GetMapping(value = "/getFavoritesEventsByUser")
    public ResponseEntity<List<LTEvent>> getFavoritesEventsByUser(@RequestParam(value = "user_id") String user_id) {
        return ResponseEntity.ok().body(service.getFavoritesEventsByUser(user_id));
    }

    @GetMapping(value = "/getAttendancesByEvent")
    public ResponseEntity<List<LTUser>> getAttendancesByEvent(@RequestParam(value = "event_id") Long event_id) {
        return ResponseEntity.ok().body(service.getAttendancesByEvent(event_id));
    }

    @PostMapping(value = "/saveFavoritesEvents")
    public ResponseEntity<Void> saveFavoritesEvents(@RequestParam(value = "event_id") Long event_id, @RequestParam(value = "user_id") String user_id) {
        service.saveFavoritesEvents(event_id, user_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/saveAttendances")
    public ResponseEntity<Void> saveAttendances(@RequestParam(value = "event_id") Long event_id, @RequestParam(value = "user_id") String user_id) {
        service.saveAttendances(event_id, user_id);
        return ResponseEntity.ok().build();
    }

}
