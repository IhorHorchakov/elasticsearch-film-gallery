package com.film.gallery.web;

import com.film.gallery.service.command.CreateFilmCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GetFilmTest extends AbstractIntegrationTest {

    @AfterEach
    public void cleanup() {
        filmService.deleteAll();
    }

    @Test
    void givenFilmIsSaved_whenGetFilm_thenExpectResponse200Status() throws Exception {
        // given
        var createFilmCommand = CreateFilmCommand.builder()
                .caption("test caption")
                .description("test description")
                .build();
        var givenFilm = filmService.create(createFilmCommand);

        // when
        var getFilmRequest = get("/api/film/%s".formatted(givenFilm.getId()));
        var getFilmResponse = this.mockMvc.perform(getFilmRequest).andDo(print());

        // then
        getFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(givenFilm.getId())))
                .andExpect(jsonPath("$.caption", is(givenFilm.getCaption())))
                .andExpect(jsonPath("$.description", is(givenFilm.getDescription())));
    }

    @Test
    void givenFilmIsNotSaved_whenGetFilm_thenExpectResponse404Status() throws Exception {
        // given no film created

        // when
        var getFilmRequest = get("/api/film/%s".formatted(randomUUID().toString()));
        var getFilmResponse = this.mockMvc.perform(getFilmRequest).andDo(print());

        // then
        getFilmResponse
                .andExpect(status().isNotFound());
    }
}
