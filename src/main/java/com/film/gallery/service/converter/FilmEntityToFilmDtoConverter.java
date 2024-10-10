package com.film.gallery.service.converter;

import com.film.gallery.repo.domain.FilmEntityDocument;
import com.film.gallery.service.dto.FilmDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmEntityToFilmDtoConverter implements Converter<FilmEntityDocument, FilmDto> {

    @Override
    public FilmDto convert(@NotNull FilmEntityDocument source) {
        return new FilmDto(source.getId(), source.getCaption(), source.getDescription());
    }

    public List<FilmDto> convert(@NotNull List<FilmEntityDocument> source) {
        return source.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
