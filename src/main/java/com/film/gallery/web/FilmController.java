package com.film.gallery.web;

import com.film.gallery.service.dto.FilmDto;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * API methods
 * <p>
 * GET    /film/:id  - returns a specific film item based on :id parameter
 * POST   /film      - creates one film item
 * PUT    /film      - updates one film item
 * POST   /films/search - searches for one or many film items
 * DELETE /films        - deletes all the films
 */
@RequestMapping("/api/films")
public interface FilmController {

    @GetMapping("/{id}")
    GetFilmResponse get(@Valid GetFilmRequest request);

    @PostMapping
    PostFilmResponse post(@RequestBody @Valid PostFilmRequest request);

    @PutMapping
    PutFilmResponse put(@RequestBody @Valid PutFilmRequest request);

    @DeleteMapping("/{id}")
    void delete(@Valid DeleteFilmRequest request);

    @PostMapping("/films/search")
    SearchFilmResponse search(@RequestBody @Valid SearchFilmRequest request);


    /* Requests and Responses */
    @Builder
    record GetFilmRequest(@NotNull String id) {
    }

    @Builder
    record GetFilmResponse(@NotNull String id, @NotBlank String caption, @NotBlank String description) {
    }

    @Builder
    record PostFilmRequest(@Nullable String id, @NotBlank String caption, @NotBlank String description) {
    }

    @Builder
    record PostFilmResponse(@NotNull String id, @NotBlank String caption, @NotBlank String description) {
    }

    @Builder
    record PutFilmRequest(@Nullable String id, @NotBlank String caption, @NotBlank String description) {
    }

    @Builder
    record PutFilmResponse(@NotNull String id, @NotBlank String caption, @NotBlank String description) {
    }

    @Builder
    record DeleteFilmRequest(@NotNull String id) {
    }

    @Builder
    record SearchFilmRequest(@NotBlank String query, @Positive Integer maxItems) {
    }

    @Builder
    record SearchFilmResponse(@NotNull Iterable<FilmDto> films) {
    }
}
