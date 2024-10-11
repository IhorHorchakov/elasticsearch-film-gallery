package com.film.gallery.service.impl;

import com.film.gallery.repo.FilmRepository;
import com.film.gallery.repo.domain.FilmEntityDocument;
import com.film.gallery.service.FilmService;
import com.film.gallery.service.command.*;
import com.film.gallery.service.converter.FilmEntityConverter;
import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.service.dto.PageDto;
import com.film.gallery.service.exception.FilmNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;
import static java.util.UUID.randomUUID;

@Service
@AllArgsConstructor
@Slf4j
public class FilmServiceImpl implements FilmService {
    private final FilmRepository repository;
    private final FilmEntityConverter converter;

    @Override
    public FilmDto get(GetFilmCommand command) {
        return repository.findById(command.id())
                .map(converter::toDto)
                .orElseThrow(() -> new FilmNotFoundException(command.id()));
    }

    @Override
    public FilmDto create(CreateFilmCommand command) {
        FilmEntityDocument entity = new FilmEntityDocument();
        entity.setId(ofNullable(command.id()).orElse(randomUUID().toString()));
        entity.setCaption(command.caption());
        entity.setDescription(command.description());
        return converter.toDto(repository.save(entity));
    }

    @Override
    public FilmDto update(UpdateFilmCommand command) {
        FilmEntityDocument entity = repository.findById(command.id())
                .orElseThrow(() -> new FilmNotFoundException(command.id()));
        entity.setCaption(command.caption());
        entity.setDescription(command.description());
        return converter.toDto(repository.save(entity));
    }

    @Override
    public void delete(DeleteFilmCommand command) {
        try {
            repository.deleteById(command.id());
        } catch (NoSuchIndexException exception) {
            log.warn("Exception occurred: {}", exception, exception.getCause());
        }
    }

    @Override
    public PageDto<FilmDto> search(SearchFilmCommand command) {
        Sort sort = ofNullable(command.paginated().getSortBy())
                .map(Sort::by)
                .orElse(Sort.unsorted());
        PageRequest pageRequest = PageRequest.of(
                command.paginated().getPageNumber(),
                command.paginated().getPageSize(),
                sort
        );
        Page<FilmEntityDocument> page = repository.search(command.query(), pageRequest);
        return converter.toPageDto(page);
    }

    @Override
    public void deleteAll() {
        try {
            repository.deleteAll();
        } catch (NoSuchIndexException exception) {
            log.warn("Exception occurred: {}", exception, exception.getCause());
        }
    }
}
