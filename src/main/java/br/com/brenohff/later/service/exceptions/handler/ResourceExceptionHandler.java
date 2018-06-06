package br.com.brenohff.later.service.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.brenohff.later.models.DetalhesErro;
import br.com.brenohff.later.service.exceptions.ObjectAlreadyExists;
import br.com.brenohff.later.service.exceptions.ObjectNotFound;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectAlreadyExists.class)
	public ResponseEntity<DetalhesErro> objectAlreadyExists(ObjectAlreadyExists e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(409l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/409");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}

	@ExceptionHandler(ObjectNotFound.class)
	public ResponseEntity<DetalhesErro> ObjectNotFound(ObjectNotFound e, HttpServletRequest request) {

		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo(e.getMessage());
		erro.setMensagemDesenvolvedor("http://erros.later.com/404");
		erro.setTimestamp(System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

}
