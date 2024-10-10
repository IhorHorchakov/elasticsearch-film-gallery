package com.film.gallery.web.mapper;

import com.film.gallery.service.command.CreateFilmCommand;
import com.film.gallery.service.command.DeleteFilmCommand;
import com.film.gallery.service.command.GetFilmCommand;
import com.film.gallery.service.command.SearchFilmCommand;
import com.film.gallery.service.command.UpdateFilmCommand;
import com.film.gallery.web.FilmController.DeleteFilmRequest;
import com.film.gallery.web.FilmController.GetFilmRequest;
import com.film.gallery.web.FilmController.PostFilmRequest;
import com.film.gallery.web.FilmController.PutFilmRequest;
import com.film.gallery.web.FilmController.SearchFilmRequest;

public interface CommandMapper {

    static GetFilmCommand mapToCommand(GetFilmRequest source) {
        return GetFilmCommand.builder()
                .id(source.id())
                .build();
    }

    static CreateFilmCommand mapToCommand(PostFilmRequest source) {
        return CreateFilmCommand.builder()
                .id(source.id())
                .caption(source.caption())
                .description(source.description())
                .build();
    }

    static UpdateFilmCommand mapToCommand(PutFilmRequest source) {
        return UpdateFilmCommand.builder()
                .id(source.id())
                .caption(source.caption())
                .description(source.description())
                .build();
    }

    static DeleteFilmCommand mapToCommand(DeleteFilmRequest source) {
        return DeleteFilmCommand.builder()
                .id(source.id())
                .build();
    }

    static SearchFilmCommand mapToCommand(SearchFilmRequest source) {
        return SearchFilmCommand.builder()
                .query(source.query())
                .build();
    }
}
