package br.com.brenohff.later.service.exceptions;

public class ObjectAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 2574717776863451995L;
	
	public ObjectAlreadyExists(String mensagem) {
		super(mensagem);
	}
	
	public ObjectAlreadyExists(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
