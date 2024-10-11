package com.film.gallery.service.command;

import com.film.gallery.service.dto.PaginatedDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record SearchFilmCommand(@NotBlank String query, @NotNull PaginatedDto paginated) {
}
