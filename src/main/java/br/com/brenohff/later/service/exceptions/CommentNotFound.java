package br.com.brenohff.later.service.exceptions;

public class CommentNotFound extends RuntimeException {

	private static final long serialVersionUID = 1351410246926387498L;

	public CommentNotFound(String mensagem) {
		super(mensagem);
	}

	public CommentNotFound(String mensagem, Throwable motivo) {
		super(mensagem, motivo);
	}

}
