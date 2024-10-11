package com.film.gallery.web;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.dto.FilmDto;
import com.film.gallery.service.dto.PaginatedDto;
import com.film.gallery.web.FilmController.SearchFilmRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import util.FileUtil;

import java.util.List;

import static org.hamcrest.Matchers.is;
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
        var paginated = PaginatedDto.of(0, 10);

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query, paginated);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmsSize", is(5)))
                .andExpect(jsonPath("$.films[0].caption", is("Fight Club")))
                .andExpect(jsonPath("$.films[1].caption", is("Schindler's List")))
                .andExpect(jsonPath("$.films[2].caption", is("Forrest Gump")))
                .andExpect(jsonPath("$.films[3].caption", is("The Matrix")))
                .andExpect(jsonPath("$.films[4].caption", is("Pulp Fiction")))
                .andExpect(jsonPath("$.pageNumber", is(0)))
                .andExpect(jsonPath("$.pagesTotal", is(1)))
                .andExpect(jsonPath("$.filmsTotal", is(5)));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInDescription_thenReturn200ResponseWith3Films() throws Exception {
        // given
        String query = "war";
        var paginated = PaginatedDto.of(0, 10);

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query, paginated);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmsSize", is(3)))
                .andExpect(jsonPath("$.films[0].caption", is("The Matrix")))
                .andExpect(jsonPath("$.films[1].caption", is("Schindler's List")))
                .andExpect(jsonPath("$.films[2].caption", is("Forrest Gump")))
                .andExpect(jsonPath("$.pageNumber", is(0)))
                .andExpect(jsonPath("$.pagesTotal", is(1)))
                .andExpect(jsonPath("$.filmsTotal", is(3)));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInCaption_thenReturn200ResponseWith1Film() throws Exception {
        // given
        String query = "Godfather";
        var paginated = PaginatedDto.of(0, 10);

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query, paginated);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmsSize", is(1)))
                .andExpect(jsonPath("$.films[0].caption", is("The Godfather")))
                .andExpect(jsonPath("$.pageNumber", is(0)))
                .andExpect(jsonPath("$.pagesTotal", is(1)))
                .andExpect(jsonPath("$.filmsTotal", is(1)));
    }

    @Test
    void givenFilmsPopulated_whenSearchByPhraseInDescription_thenReturn200ResponseWith1Film() throws Exception {
        // given
        String query = "Gotham";
        var paginated = PaginatedDto.of(0, 10);

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query, paginated);
        var searchFilmRequest = post("/api/films/search")
                .content(OBJECT_MAPPER.writeValueAsBytes(searchFilmRequestBody))
                .contentType(MediaType.APPLICATION_JSON);
        var postFilmResponse = this.mockMvc.perform(searchFilmRequest)
                .andDo(print());

        // then
        postFilmResponse
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmsSize", is(1)))
                .andExpect(jsonPath("$.films[0].caption", is("The Dark Knight")))
                .andExpect(jsonPath("$.pageNumber", is(0)))
                .andExpect(jsonPath("$.pagesTotal", is(1)))
                .andExpect(jsonPath("$.filmsTotal", is(1)));
    }

    @Test
    void givenFilmsPopulated_whenSearchByEmptyQuery_thenReturn500Response() throws Exception {
        // given
        String query = "";
        var paginated = PaginatedDto.of(0, 10);

        // when
        var searchFilmRequestBody = new SearchFilmRequest(query, paginated);
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
