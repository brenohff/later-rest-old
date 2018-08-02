package br.com.brenohff.later.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_EVENT")
@IdClass(value = LTCategoryEventPK.class)
public class LTCategoryEvent implements Serializable {

	private static final long serialVersionUID = 2334421866476625056L;

	@Id
	private Long category_id;

	@Id
	private Long event_id;

	public LTCategoryEvent() {

	}

	public LTCategoryEvent(Long category_id, Long event_id) {
		super();
		this.category_id = category_id;
		this.event_id = event_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

}
