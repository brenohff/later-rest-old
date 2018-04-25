package br.com.brenohff.later.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "CATEGORY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTCategory implements Serializable{

	private static final long serialVersionUID = 7158436812533878831L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String url;
	private String name;
	private String baseColor;
	
	public LTCategory() {
	}

	public LTCategory(String url, String name, String baseColor) {
		super();
		this.url = url;
		this.name = name;
		this.baseColor = baseColor;
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
	
	

}
