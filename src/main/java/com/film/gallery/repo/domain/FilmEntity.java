package com.film.gallery.repo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "film")
@Data
@NoArgsConstructor
public class FilmEntity {
    @Id
    private String id;

    @Column(name = "caption")
    @NotBlank
    private String caption;

    @Column(name = "description")
    @Size(max = 2000)
    @NotBlank
    private String description;
}
