package br.com.brenohff.later.service.exceptions;

public class UserNotFound extends RuntimeException {
	
	private static final long serialVersionUID = 4697445756037773654L;

	public UserNotFound(String mensagem) {
		super(mensagem);
	}
	
	public UserNotFound(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
