package br.com.brenohff.later.api;

import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {

	@Autowired
	UserService service;

	@GetMapping(value = "/getAll")
	public List<LTUser> getAllUsers() {
		return service.getAll();
	}

	@GetMapping(value = "/getUserById")
	public LTUser getByFaceID(@RequestParam(value="id") String id) {
		return service.getUserByID(id);
	}

    @GetMapping(value = "/getAttendancesByEvent")
    public ResponseEntity<List<LTUser>> getAttendancesByEvent(@RequestParam(value = "event_id") Long event_id) {
        return ResponseEntity.ok().body(service.getAttendancesByEvent(event_id));
    }

	@PostMapping(value = "/saveUser")
	public void saveUser(@RequestBody LTUser user) {
		service.saveUser(user);
	}

}
