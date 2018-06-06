package br.com.brenohff.later.service.exceptions;

public class ObjectNotFound extends RuntimeException {
	private static final long serialVersionUID = 8950375360783014470L;

	public ObjectNotFound(String mensagem) {
		super(mensagem);
	}

	public ObjectNotFound(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
