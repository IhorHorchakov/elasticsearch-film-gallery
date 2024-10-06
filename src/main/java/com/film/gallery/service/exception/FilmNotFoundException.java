package com.film.gallery.service.exception;

import org.springframework.http.HttpStatus;

public class FilmNotFoundException extends ServiceException {
    private static final String ERROR_MESSAGE = "The film by id = '%s' is not found";

    public FilmNotFoundException(String id) {
        super(ERROR_MESSAGE.formatted(id), HttpStatus.NOT_FOUND);
    }
}
