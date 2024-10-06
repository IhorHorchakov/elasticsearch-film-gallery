package com.film.gallery.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilmDto {
    private String id;
    private String caption;
    private String description;
}
