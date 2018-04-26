package br.com.brenohff.later.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "INFO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTVersion implements Serializable {

	private static final long serialVersionUID = 2036535387284484618L;

	@Id
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date last_update;
	private String version;
	private String author;
	private String contact;
	private boolean working;

	public LTVersion() {
	}

	public LTVersion(String version, String author, String contact, boolean working) {
		super();
		this.last_update = new Date();
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

	public Date getLast_update() {
		return last_update;
	}

	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}

}
