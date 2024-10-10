package com.film.gallery.service;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.command.DeleteFilmCommand;
import com.film.gallery.service.command.GetFilmCommand;
import com.film.gallery.service.command.SearchFilmCommand;
import com.film.gallery.service.command.UpdateFilmCommand;
import com.film.gallery.service.dto.FilmDto;

import java.util.List;

public interface FilmService {
    FilmDto get(GetFilmCommand command);
    FilmDto create(CreateFilmCommand command);
    FilmDto update(UpdateFilmCommand command);
    void delete(DeleteFilmCommand command);
    void deleteAll();
    List<FilmDto> search(SearchFilmCommand command);
}
