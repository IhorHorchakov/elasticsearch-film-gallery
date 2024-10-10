package com.film.gallery.service.converter;

import com.film.gallery.repo.domain.FilmEntityDocument;
import com.film.gallery.service.dto.FilmDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilmEntityToFilmDtoConverter {

    public FilmDto toDto(@NotNull FilmEntityDocument source) {
        return new FilmDto(source.getId(), source.getCaption(), source.getDescription());
    }

    public List<FilmDto> toPageDto(@NotNull List<FilmEntityDocument> source) {
        return source.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
