package com.film.gallery.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ServiceException extends RuntimeException {
    private HttpStatus httpStatus;

    public ServiceException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }
}
