package com.film.gallery.web;

import com.film.gallery.web.FilmController.PostFilmRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CreateFilmTest extends AbstractIntegrationTest {

    @AfterEach
    public void cleanup() {
        filmService.deleteAll();
    }

    @Test
    void givenNoFilmIsCreated_whenPostFilm_thenExpectResponse200Status() throws Exception {
        // given no film is created
        String filmId = randomUUID().toString();

        // when
        var postFilmRequestBody = new PostFilmRequest(
                filmId,
                "test caption",
                "test description"
        );
        var postFilmRequest = post("/api/films")
                .content(OBJECT_MAPPER.writeValueAsBytes(postFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(postFilmRequest).andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(postFilmRequestBody.id())))
                .andExpect(jsonPath("$.caption", is(postFilmRequestBody.caption())))
                .andExpect(jsonPath("$.description", is(postFilmRequestBody.description())));
    }

    @Test
    void givenNoFilmIsCreated_whenPostFilmWithoutId_thenExpectResponse200Status() throws Exception {
        // given no film is created
        String filmId = null;

        // when
        var postFilmRequestBody = new PostFilmRequest(
                filmId,
                "test caption",
                "test description"
        );
        var postFilmRequest = post("/api/films")
                .content(OBJECT_MAPPER.writeValueAsBytes(postFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(postFilmRequest).andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.caption", is(postFilmRequestBody.caption())))
                .andExpect(jsonPath("$.description", is(postFilmRequestBody.description())));
    }

}
