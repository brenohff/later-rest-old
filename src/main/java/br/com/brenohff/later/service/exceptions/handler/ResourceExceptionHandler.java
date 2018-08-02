package br.com.brenohff.later.service.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import br.com.brenohff.later.service.exceptions.FileException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.brenohff.later.model.DetalhesErro;
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

    @ExceptionHandler(FileException.class)
    public ResponseEntity<DetalhesErro> fileException(FileException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getBadRequest(400l, e.getMessage()));
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<DetalhesErro> amazonException(AmazonServiceException e, HttpServletRequest request) {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(Long.parseLong(e.getErrorCode()));
        erro.setTitulo(e.getMessage());
        erro.setMensagemDesenvolvedor("http://erros.later.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode())).body(erro);
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<DetalhesErro> amazonClientException(AmazonClientException e, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getBadRequest(400l, e.getMessage()));
    }

    private DetalhesErro getBadRequest(Long status, String error) {
        DetalhesErro erro = new DetalhesErro();
        erro.setStatus(status);
        erro.setTitulo(error);
        erro.setMensagemDesenvolvedor("http://erros.later.com/404");
        erro.setTimestamp(System.currentTimeMillis());

        return erro;
    }

}
