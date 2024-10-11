package com.film.gallery.service.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginatedDto {
    @NotNull
    private Integer pageNumber;
    @NotNull
    private Integer pageSize;
    @Nullable
    private String sortBy;

    public PaginatedDto(Integer pageNumber, Integer pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public PaginatedDto(Integer pageNumber, Integer pageSize, String sortBy) {
        this(pageNumber, pageSize);
        this.sortBy = sortBy;
    }

    public static PaginatedDto of(Integer pageNumber, Integer pageSize) {
        return new PaginatedDto(pageNumber, pageSize);
    }


}
