package com.film.gallery.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {
    @NotNull
    private List<T> elements;
    private int elementsSize;
    private int pageNumber;
    private int pagesTotal;
    private long elementsTotal;
}
