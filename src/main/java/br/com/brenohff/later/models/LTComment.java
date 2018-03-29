package br.com.brenohff.later.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "COMMENT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTComment implements Serializable {

	private static final long serialVersionUID = 8114551962325291213L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "event_id", nullable = false)
	private LTEvent event;

	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private LTUser user;

	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LTEvent getEvent() {
		return event;
	}

	public void setEvent(LTEvent event) {
		this.event = event;
	}

	public LTUser getUser() {
		return user;
	}

	public void setUser(LTUser user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
