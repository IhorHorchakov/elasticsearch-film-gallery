package com.film.gallery.web.mapper;

import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.web.FilmController.GetFilmResponse;
import com.film.gallery.web.FilmController.PostFilmResponse;
import com.film.gallery.web.FilmController.PutFilmResponse;
import com.film.gallery.web.FilmController.SearchFilmResponse;

import java.util.List;

public interface ResponseMapper {

    static GetFilmResponse mapToGetFilmResponse(FilmDto source) {
        return GetFilmResponse.builder()
                .id(source.getId())
                .caption(source.getCaption())
                .description(source.getDescription())
                .build();
    }

    static SearchFilmResponse mapToSearchFilmResponse(List<FilmDto> source) {
        return SearchFilmResponse.builder()
                .films(source)
                .size(source.size())
                .build();
    }

    static PostFilmResponse mapToPostFilmResponse(FilmDto source) {
        return PostFilmResponse.builder()
                .id(source.getId())
                .caption(source.getCaption())
                .description(source.getDescription())
                .build();
    }

    static PutFilmResponse mapToPutFilmResponse(FilmDto source) {
        return PutFilmResponse.builder()
                .id(source.getId())
                .caption(source.getCaption())
                .description(source.getDescription())
                .build();
    }
}
