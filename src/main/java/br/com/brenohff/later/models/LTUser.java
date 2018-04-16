package br.com.brenohff.later.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "USER")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTUser implements Serializable {

	private static final long serialVersionUID = 9077168091743018235L;

	@Id
	private String face_id;

	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "user")
	private Set<LTEvent> events;

	@OneToMany(mappedBy = "user")
	private Set<LTComment> comments;

	private String name;
	private String birthday;
	private String gender;
	private String link;
	private String image;
	private String image_long;


	public String getFace_id() {
		return face_id;
	}

	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage_long() {
		return image_long;
	}

	public void setImage_long(String image_long) {
		this.image_long = image_long;
	}

	@JsonIgnore
	public Set<LTEvent> getEvents() {
		return events;
	}

	public void setEvents(Set<LTEvent> events) {
		this.events = events;
	}

	@JsonIgnore
	public Set<LTComment> getComments() {
		return comments;
	}

	public void setComments(Set<LTComment> comments) {
		this.comments = comments;
	}

}
