package com.film.gallery.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film.gallery.config.containers.ElasticsearchTestContainer;
import com.film.gallery.service.FilmService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.elasticsearch.ElasticsearchContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {
    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Container
    protected static ElasticsearchContainer elasticsearchContainer = new ElasticsearchTestContainer();
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected FilmService filmService;

    @BeforeAll
    static void setUp() {
        elasticsearchContainer.start();
    }

    @AfterAll
    static void destroy() {
        elasticsearchContainer.stop();
    }

}



