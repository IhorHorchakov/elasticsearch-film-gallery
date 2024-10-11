package com.film.gallery.service.converter;

import com.film.gallery.repo.domain.FilmEntityDocument;
import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.service.dto.PageDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class FilmEntityConverter {

    public FilmDto toDto(@NotNull FilmEntityDocument source) {
        return new FilmDto(source.getId(), source.getCaption(), source.getDescription());
    }

    public PageDto<FilmDto> toPageDto(@NotNull Page<FilmEntityDocument> source) {
        var elements = source.stream()
                .map(this::toDto)
                .toList();
        var target = new PageDto<FilmDto>();
        target.setElements(elements);
        target.setElementsSize(elements.size());
        target.setPageNumber(source.getNumber());
        target.setPagesTotal(source.getTotalPages());
        target.setElementsTotal(source.getTotalElements());
        return target;
    }
}
