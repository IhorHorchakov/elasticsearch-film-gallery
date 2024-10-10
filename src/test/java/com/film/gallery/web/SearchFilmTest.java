package com.film.gallery.web;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.web.FilmController.PostFilmRequest;
import com.film.gallery.web.FilmController.SearchFilmRequest;
import com.film.gallery.web.FilmController.SearchFilmResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import util.FileUtil;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SearchFilmTest extends AbstractIntegrationTest {
    private static final String FILMS_SAMPLER_PATH = "films/10-films-sampler.json";

    @BeforeEach
    public void setup() throws Exception {
        populateFilms(FILMS_SAMPLER_PATH);
    }

    @AfterEach
    public void cleanup() {
        filmService.deleteAll();
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhrase_thenReturn200ResponseWith5Films() throws Exception {
        // given
        String query = "insomniac office worker in german-occupied Vietnam";

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(5)))
                .andExpect(jsonPath("$.films[0].caption", is("Fight Club")))
                .andExpect(jsonPath("$.films[1].caption", is("Schindler's List")))
                .andExpect(jsonPath("$.films[2].caption", is("Forrest Gump")))
                .andExpect(jsonPath("$.films[3].caption", is("The Matrix")))
                .andExpect(jsonPath("$.films[4].caption", is("Pulp Fiction")));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInDescription_thenReturn200ResponseWith3Films() throws Exception {
        // given
        String query = "war";

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(3)))
                .andExpect(jsonPath("$.films[0].caption", is("The Matrix")))
                .andExpect(jsonPath("$.films[1].caption", is("Schindler's List")))
                .andExpect(jsonPath("$.films[2].caption", is("Forrest Gump")));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInCaption_thenReturn200ResponseWith1Film() throws Exception {
        // given
        String query = "Godfather";

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(1)))
                .andExpect(jsonPath("$.films[0].caption", is("The Godfather")));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInDescription_thenReturn200ResponseWith1Film() throws Exception {
        // given
        String query = "Gotham";

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size", is(1)))
                .andExpect(jsonPath("$.films[0].caption", is("The Dark Knight")));
    }

    @Test
    void givenFilmsPopulated_whenSearchByEmptyQuery_thenReturn500Response() throws Exception {
        // given
        String query = "";

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var searchFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        searchFilmResponse
                .andExpect(status().isInternalServerError());
    }

    public void populateFilms(String filePath) throws Exception {
        List<FilmDto> films = FileUtil.readFilmsFromResources(filePath);
        films.forEach(film -> {
            var command = new CreateFilmCommand(film.getId(), film.getCaption(), film.getDescription());
            filmService.create(command);
        });
    }
}
