package br.com.brenohff.later.service.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.brenohff.later.models.DetalhesErro;
import br.com.brenohff.later.service.exceptions.UserAlreadyExistsException;
import br.com.brenohff.later.service.exceptions.UserNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<DetalhesErro> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo("O usuário já existe.");
		erro.setMensagemDesenvolvedor("http://erros.later.com/409");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}

	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<DetalhesErro> userNotFound(UserNotFound e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("Usuário inexistente.");
		erro.setMensagemDesenvolvedor("http://erros.later.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
