package com.film.gallery.service.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateFilmCommand(@NotBlank String id, @NotBlank String caption, @NotBlank String description) {
}
