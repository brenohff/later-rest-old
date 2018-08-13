package br.com.brenohff.later.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CHAT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTChat implements Serializable {

	private static final long serialVersionUID = 3625234538742558082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "user_id", nullable = false)
	private LTUser user;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dtPost;

	private MessageType type;
	private String content;
	private String eventId;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LTUser getUser() {
		return user;
	}

	public void setUser(LTUser user) {
		this.user = user;
	}

	public Date getDtPost() {
		return dtPost;
	}

	public void setDtPost(Date dtPost) {
		this.dtPost = dtPost;
	}
	
}
