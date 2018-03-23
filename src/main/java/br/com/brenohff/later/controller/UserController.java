package br.com.brenohff.later.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTUser;
import br.com.brenohff.later.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService service;

	@RequestMapping(value = "/getAllUsers")
	public List<LTUser> getAllUsers() {
		return service.getAll();
	}
	
	@RequestMapping(value = "/getUser/{face_id}")
	public LTUser getByFaceID(@PathVariable("face_id") String face_id) {
		return service.getUserByFaceID(face_id);
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public void saveUser(@RequestBody LTUser user) {
		service.saveUser(user);
	}

}
