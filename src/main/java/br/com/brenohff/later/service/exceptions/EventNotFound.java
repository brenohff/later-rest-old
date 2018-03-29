package br.com.brenohff.later.service.exceptions;

public class EventNotFound extends RuntimeException{

	private static final long serialVersionUID = 8228815833199353429L;

	public EventNotFound(String mensagem) {
		super(mensagem);
	}
	
	public EventNotFound(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
