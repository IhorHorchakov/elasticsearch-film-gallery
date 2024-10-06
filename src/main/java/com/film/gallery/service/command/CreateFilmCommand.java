package com.film.gallery.service.command;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateFilmCommand(@Nullable String id, @NotBlank String caption, @NotBlank String description) {
}
