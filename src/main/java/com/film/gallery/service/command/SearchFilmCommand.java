package com.film.gallery.service.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record SearchFilmCommand(@NotBlank String query, @Positive Integer maxItems) {
}
