package com.film.gallery.service;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.command.GetFilmCommand;
import com.film.gallery.service.command.SearchFilmCommand;
import com.film.gallery.service.command.UpdateFilmCommand;
import com.film.gallery.service.dto.FilmDto;

public interface FilmService {
    FilmDto get(GetFilmCommand command);
    FilmDto create(CreateFilmCommand command);
    FilmDto update(UpdateFilmCommand command);
    void deleteAll();
    Iterable<FilmDto> search(SearchFilmCommand command);
}
