package br.com.brenohff.later.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@RequestMapping(value = "/version")
	public ResponseEntity<Void> makeTest() {
		return ResponseEntity.ok().build();
	}

}
