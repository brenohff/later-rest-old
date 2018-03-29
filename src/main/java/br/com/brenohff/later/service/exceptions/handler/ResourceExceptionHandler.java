package br.com.brenohff.later.service.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.brenohff.later.models.DetalhesErro;
import br.com.brenohff.later.service.exceptions.CommentNotFound;
import br.com.brenohff.later.service.exceptions.EventNotFound;
import br.com.brenohff.later.service.exceptions.UserAlreadyExistsException;
import br.com.brenohff.later.service.exceptions.UserNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<DetalhesErro> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/409");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<DetalhesErro> userNotFound(UserNotFound e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(EventNotFound.class)
	public ResponseEntity<DetalhesErro> eventNotFound(EventNotFound e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<DetalhesErro> commentNotFound(CommentNotFound e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
