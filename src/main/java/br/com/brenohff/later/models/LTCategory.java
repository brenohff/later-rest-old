package br.com.brenohff.later.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CATEGORY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTCategory implements Serializable {

	private static final long serialVersionUID = 7158436812533878831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "category_event", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = {
			@JoinColumn(name = "event_id") })

	Set<LTEvent> events = new HashSet<>();

	private String url;
	private String name;
	private String baseColor;
	private String baseColor700;

	public LTCategory() {
	}

	public LTCategory(String url, String name, String baseColor, String baseColor700) {
		super();
		this.url = url;
		this.name = name;
		this.baseColor = baseColor;
		this.baseColor700 = baseColor700;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBaseColor() {
		return baseColor;
	}

	public void setBaseColor(String baseColor) {
		this.baseColor = baseColor;
	}

	public String getBaseColor700() {
		return baseColor700;
	}

	public void setBaseColor700(String baseColor700) {
		this.baseColor700 = baseColor700;
	}

	@JsonIgnore
	public Set<LTEvent> getEvents() {
		return events;
	}

	public void setEvents(Set<LTEvent> events) {
		this.events = events;
	}

}
