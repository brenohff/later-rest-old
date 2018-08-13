package br.com.brenohff.later.model;

import java.io.Serializable;

public class LTCategoryEventPK implements Serializable {

	private static final long serialVersionUID = -5700333885181180979L;

	private long category_id;
	private Long event_id;

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public Long getEvent_id() {
		return event_id;
	}

	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

}
