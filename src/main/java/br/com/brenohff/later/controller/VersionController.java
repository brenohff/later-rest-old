package br.com.brenohff.later.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

	@RequestMapping(value = { "/", "/version" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Data> sayHello() {
		Data data = new Data("0.0.2-SNAPSHOT", "Breno Henrique", "brenohff@gmail.com", true);
		return ResponseEntity.status(HttpStatus.OK).body(data);
	}

}

class Data {

	private String version;
	private String author;
	private String contact;
	private boolean working;

	public Data(String version, String author, String contact, boolean working) {
		super();
		this.version = version;
		this.author = author;
		this.contact = contact;
		this.working = working;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public boolean isWorking() {
		return working;
	}

	public void setWorking(boolean working) {
		this.working = working;
	}

}
