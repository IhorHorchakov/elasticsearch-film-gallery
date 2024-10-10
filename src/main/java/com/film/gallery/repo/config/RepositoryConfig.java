package com.film.gallery.repo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(
//        basePackages = "org.springframework.data.elasticsearch.repositories"
//        repositoryBaseClass = FilmRepositoryImpl.class
)
class RepositoryConfig {
}
