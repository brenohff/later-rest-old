package br.com.brenohff.later.service.exceptions;

public class FileException extends RuntimeException {

    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
