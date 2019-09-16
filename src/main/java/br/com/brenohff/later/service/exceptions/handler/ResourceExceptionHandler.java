package br.com.brenohff.later.service.exceptions.handler;

import br.com.brenohff.later.model.DetalhesErro;
import br.com.brenohff.later.service.exceptions.FileException;
import br.com.brenohff.later.service.exceptions.ObjectAlreadyExists;
import br.com.brenohff.later.service.exceptions.ObjectNotFound;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExists.class)
    public ResponseEntity<DetalhesErro> objectAlreadyExists(ObjectAlreadyExists e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(getBadRequest(409L, e.getMessage()));
    }

    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<DetalhesErro> ObjectNotFound(ObjectNotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(getBadRequest(404L, e.getMessage()));
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<DetalhesErro> fileException(FileException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getBadRequest(400L, e.getMessage()));
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<DetalhesErro> amazonException(AmazonServiceException e) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getStatusCode())).body(getBadRequest(Long.parseLong(e.getErrorCode()), e.getMessage()));
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<DetalhesErro> amazonClientException(AmazonClientException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getBadRequest(400L, e.getMessage()));
    }

    private DetalhesErro getBadRequest(Long status, String error) {
        DetalhesErro erro = new DetalhesErro();

        erro.setStatus(status);
        erro.setTitulo(error);
        erro.setMensagemDesenvolvedor("http://erros.later.com/" + Double.toString(status));
        erro.setTimestamp(System.currentTimeMillis());

        return erro;
    }

}