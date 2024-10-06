package com.film.gallery.web;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.web.FilmController.PutFilmRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UpdateFilmTest extends AbstractIntegrationTest {

    @AfterEach
    public void cleanup() {
        filmService.deleteAll();
    }

    @Test
    void givenFilmIsCreated_whenPutUpdatedFilm_thenExpectResponse200Status() throws Exception {
        // given
        var givenFilm = filmService.create(new CreateFilmCommand(
                randomUUID().toString(),
                "test caption",
                "test description")
        );

        // when
        givenFilm.setCaption("updated caption");
        givenFilm.setDescription("updated description");
        var putFilmRequest = put("/api/film")
                .content(OBJECT_MAPPER.writeValueAsBytes(new PutFilmRequest(
                        givenFilm.getId(),
                        givenFilm.getCaption(),
                        givenFilm.getDescription()
                )))
                .contentType(MediaType.APPLICATION_JSON);
        var putFilmResponse = this.mockMvc.perform(putFilmRequest).andDo(print());

        // then
        putFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(givenFilm.getId())))
                .andExpect(jsonPath("$.caption", is(givenFilm.getCaption())))
                .andExpect(jsonPath("$.description", is(givenFilm.getDescription())));
    }
}
