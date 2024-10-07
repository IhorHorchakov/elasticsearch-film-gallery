package com.film.gallery.service.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DeleteFilmCommand(@NotBlank String id) {
}
