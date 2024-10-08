package com.film.gallery.repo;

import com.film.gallery.repo.domain.FilmEntityDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends ElasticsearchRepository<FilmEntityDocument, String> {
}
