package util;

import com.film.gallery.service.dto.FilmDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtil {
    private final static JSONParser JSON_PARSER = new JSONParser();

    public static List<FilmDto> readFilmsFromResources(String filePath) throws Exception {
        var filmsJsonArray = readJsonArrayFromResources(filePath);
        List<FilmDto> films = new ArrayList<>();
        Iterator<JSONObject> iterator = filmsJsonArray.iterator();
        while (iterator.hasNext()) {
            JSONObject filmJsonObject = iterator.next();
            FilmDto film = new FilmDto(
                    String.valueOf(filmJsonObject.get("id")),
                    String.valueOf(filmJsonObject.get("caption")),
                    String.valueOf(filmJsonObject.get("description"))
            );
            films.add(film);
        }
        return films;
    }

    private static JSONArray readJsonArrayFromResources(String filePath) throws Exception {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        return (JSONArray) JSON_PARSER.parse(new InputStreamReader(inputStream));
    }
}
