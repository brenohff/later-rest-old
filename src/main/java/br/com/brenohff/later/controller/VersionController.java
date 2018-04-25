package br.com.brenohff.later.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@RequestMapping(value = {"/", "/version"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> sayHello() {
	    HashMap<String, String> map = new HashMap<>();
	    map.put("version", "v1");
	    map.put("date", new Date().toString());
	    map.put("autor", "Breno Henrique");
	    map.put("working", "true");
	    return map;
	}

}
