package com.film.gallery.service.impl;

import com.film.gallery.repo.FilmRepository;
import com.film.gallery.repo.domain.FilmEntity;
import com.film.gallery.service.FilmService;
import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.command.GetFilmCommand;
import com.film.gallery.service.command.SearchFilmCommand;
import com.film.gallery.service.command.UpdateFilmCommand;
import com.film.gallery.service.converter.FilmEntityToFilmDtoConverter;
import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.service.exception.FilmNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static java.util.Optional.ofNullable;
import static java.util.UUID.randomUUID;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;
    private final FilmEntityToFilmDtoConverter entityToDtoConverter;

    @Override
    public FilmDto get(GetFilmCommand command) {
        return repository.findById(command.id())
                .map(entityToDtoConverter::convert)
                .orElseThrow(() -> new FilmNotFoundException(command.id()));
    }

    @Override
    public FilmDto create(CreateFilmCommand command) {
        FilmEntity entity = new FilmEntity();
        entity.setId(ofNullable(command.id()).orElse(randomUUID().toString()));
        entity.setCaption(command.caption());
        entity.setDescription(command.description());
        return entityToDtoConverter.convert(repository.save(entity));
    }

    @Override
    public FilmDto update(UpdateFilmCommand command) {
        FilmEntity entity = repository.findById(command.id())
                .orElseThrow(() -> new FilmNotFoundException(command.id()));
        entity.setCaption(command.caption());
        entity.setDescription(command.description());
        return entityToDtoConverter.convert(repository.save(entity));
    }

    @Override
    public Iterable<FilmDto> search(SearchFilmCommand command) {
        return Collections.emptyList();
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
