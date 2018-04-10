package br.com.brenohff.later.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@RequestMapping(value = "/version", produces = "application/json")
	public String makeTest() {
		
		return JSONObject.quote("v1");
	}

}
