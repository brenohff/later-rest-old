package br.com.brenohff.later.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.brenohff.later.model.LTUser;
import br.com.brenohff.later.service.UserService;

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

	@PostMapping(value = "/saveUser")
	public void saveUser(@RequestBody LTUser user) {
		service.saveUser(user);
	}

}
