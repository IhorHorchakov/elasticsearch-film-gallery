package com.film.gallery.web.impl;

import com.film.gallery.service.FilmService;
import com.film.gallery.web.FilmController;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import static com.film.gallery.web.mapper.CommandMapper.mapToCommand;
import static com.film.gallery.web.mapper.ResponseMapper.mapToGetFilmResponse;
import static com.film.gallery.web.mapper.ResponseMapper.mapToPostFilmResponse;
import static com.film.gallery.web.mapper.ResponseMapper.mapToPutFilmResponse;
import static com.film.gallery.web.mapper.ResponseMapper.mapToSearchFilmResponse;

@RestController
@AllArgsConstructor
public class FilmControllerImpl implements FilmController {
    private final FilmService filmService;

    @Override
    public GetFilmResponse get(GetFilmRequest request) {
        return mapToGetFilmResponse(filmService.get(mapToCommand(request)));
    }

    @Override
    public PostFilmResponse post(PostFilmRequest request) {
        return mapToPostFilmResponse(filmService.create(mapToCommand(request)));
    }

    @Override
    public PutFilmResponse put(PutFilmRequest request) {
        return mapToPutFilmResponse(filmService.update(mapToCommand(request)));
    }

    @Override
    public SearchFilmResponse search(SearchFilmRequest request) {
        return mapToSearchFilmResponse(filmService.search(mapToCommand(request)));
    }

    @Override
    public void deleteAll() {
        filmService.deleteAll();
    }
}
