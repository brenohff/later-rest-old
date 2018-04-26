package br.com.brenohff.later.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brenohff.later.models.LTVersion;
import br.com.brenohff.later.repository.VersionRepository;

@RestController
public class VersionController {

	@Autowired
	VersionRepository repository;

	@RequestMapping(value = { "/", "/version" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LTVersion> getVersion() {
		LTVersion version = repository.findAll().get(0);
		return ResponseEntity.status(HttpStatus.OK).body(version);
	}

	// TODO APAGAR ESTE MÉTODO
	@RequestMapping(value = "/initiate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LTVersion> initiate() {
		LTVersion version = new LTVersion("0.0.3-SNAPSHOT", "Breno Henrique", "brenohff@gmail.com", true);
		repository.save(version);
		return ResponseEntity.status(HttpStatus.OK).body(version);
	}

}
