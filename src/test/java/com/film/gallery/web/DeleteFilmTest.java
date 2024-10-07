package com.film.gallery.web;

import com.film.gallery.service.command.CreateFilmCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class DeleteFilmTest extends AbstractIntegrationTest {

    @AfterEach
    public void cleanup() {
        filmService.deleteAll();
    }

    @Test
    void givenFilmIsSaved_whenDeleteFilm_thenExpectResponse200Status() throws Exception {
        // given
        var createFilmCommand = CreateFilmCommand.builder()
                .caption("test caption")
                .description("test description")
                .build();
        var givenFilm = filmService.create(createFilmCommand);

        // when
        var deleteFilmRequest = delete("/api/films/%s".formatted(givenFilm.getId()));
        var deleteFilmResponse = this.mockMvc.perform(deleteFilmRequest).andDo(print());

        // then
        deleteFilmResponse
                .andExpect(status().isOk());
    }

    @Test
    void givenFilmIsNotSaved_whenDeleteFilm_thenExpectResponse200Status() throws Exception {
        // given no film created
        String filmId = randomUUID().toString();

        // when
        var getFilmRequest = delete("/api/films/%s".formatted(filmId));
        var getFilmResponse = this.mockMvc.perform(getFilmRequest).andDo(print());

        // then
        getFilmResponse
                .andExpect(status().isOk());
    }
}
