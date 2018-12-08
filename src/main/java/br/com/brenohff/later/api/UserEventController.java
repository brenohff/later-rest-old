package br.com.brenohff.later.api;

import br.com.brenohff.later.model.LTEvent;
import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.model.LTUserEventAttendances;
import br.com.brenohff.later.model.LTUserEventFavorites;
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

    @PostMapping(value = "/saveFavoritesEvents")
    public ResponseEntity<Void> saveFavoritesEvents(@RequestBody LTUserEventFavorites favorites) {
        service.saveFavoritesEvents(favorites.getEvent_id(), favorites.getUser_id());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/deleteFavoritesEvents")
    public ResponseEntity<Void> deleteFavoritesEvents(@RequestBody LTUserEventFavorites favorites) {
        service.deleteFavoritesEvents(favorites.getEvent_id(), favorites.getUser_id());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getFavoritesEventsByUser")
    public ResponseEntity<List<LTEvent>> getFavoritesEventsByUser(@RequestParam(value = "user_id") String user_id) {
        return ResponseEntity.ok().body(service.getFavoritesEventsByUser(user_id));
    }

    //====================================== ATTENDANCES ======================================

    @PostMapping(value = "/saveAttendances")
    public ResponseEntity<Void> saveAttendances(@RequestBody LTUserEventAttendances attendances) {
        service.saveAttendances(attendances.getEvent_id(), attendances.getUser_id());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/deleteAttendances")
    public ResponseEntity<Void> deleteAttendances(@RequestBody LTUserEventAttendances attendances) {
        service.deleteAttendances(attendances.getEvent_id(), attendances.getUser_id());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getAttendancesByEvent")
    public ResponseEntity<List<LTUser>> getAttendancesByEvent(@RequestParam(value = "event_id") Long event_id) {
        return ResponseEntity.ok().body(service.getAttendancesByEvent(event_id));
    }

}
