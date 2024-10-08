package com.film.gallery.repo.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "film")
@Data
@NoArgsConstructor
public class FilmEntityDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "caption")
    @NotBlank
    private String caption;

    @Field(type = FieldType.Text, name = "description")
    @NotBlank
    private String description;
}
