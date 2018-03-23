package br.com.brenohff.later.models;

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
@Table(name = "EVENT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTEvent implements Serializable{

	private static final long serialVersionUID = -1082383445658167904L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne()
    @JoinColumn(name="user_id", nullable = false)
    private LTUser user;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dt_publicacao = new Date();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dt_evento = new Date();
	
	private String titulo;
	private String descricao;
	private double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LTUser getUser() {
		return user;
	}

	public void setUser(LTUser user) {
		this.user = user;
	}

	public Date getDt_publicacao() {
		return dt_publicacao;
	}

	public void setDt_publicacao(Date dt_publicacao) {
		this.dt_publicacao = dt_publicacao;
	}

	public Date getDt_evento() {
		return dt_evento;
	}

	public void setDt_evento(Date dt_evento) {
		this.dt_evento = dt_evento;
	}
	
	

}
