package br.com.brenohff.later.service.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 2574717776863451995L;
	
	public UserAlreadyExistsException(String mensagem) {
		super(mensagem);
	}
	
	public UserAlreadyExistsException(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
