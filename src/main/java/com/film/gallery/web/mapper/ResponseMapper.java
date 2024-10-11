package com.film.gallery.web.mapper;

import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.service.dto.PageDto;
import com.film.gallery.web.FilmController.GetFilmResponse;
import com.film.gallery.web.FilmController.PostFilmResponse;
import com.film.gallery.web.FilmController.PutFilmResponse;
import com.film.gallery.web.FilmController.SearchFilmResponse;

public interface ResponseMapper {

    static GetFilmResponse mapToGetFilmResponse(FilmDto source) {
        return GetFilmResponse.builder()
                .id(source.getId())
                .caption(source.getCaption())
                .description(source.getDescription())
                .build();
    }

    static SearchFilmResponse mapToSearchFilmResponse(PageDto<FilmDto> page) {
        return SearchFilmResponse.builder()
                .films(page.getElements())
                .filmsSize(page.getElementsSize())
                .filmsTotal(page.getElementsTotal())
                .pageNumber(page.getPageNumber())
                .pagesTotal(page.getPagesTotal())
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
