package com.film.gallery.repo;

import com.film.gallery.repo.domain.FilmEntityDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends ElasticsearchRepository<FilmEntityDocument, String> {

    @Query("""
            {
              "multi_match": {
              "query" : "#{#query}",
              "fields": ["caption", "description"]
              }
            }
    """)
    Page<FilmEntityDocument> search(String query, Pageable pageable);
}
