package com.film.gallery.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film.gallery.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {
    public final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected FilmService filmService;
}
