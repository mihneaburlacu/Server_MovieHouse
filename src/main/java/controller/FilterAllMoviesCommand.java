package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class FilterAllMoviesCommand implements ICommand{
    private static final DBContextMovie dbContextMovie = new DBContextMovie();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        String[] split = data.split(",");
        String select = split[0];
        String got = split[1];

        List<Movie> list = new ArrayList<>();

        if(select.equalsIgnoreCase("Type") || select.equalsIgnoreCase("Typ") || select.equalsIgnoreCase("Tip")) {
            list = dbContextMovie.findAllMovies(dbContextMovie.createFindByTypeQuery(got));
        }
        else if(select.equalsIgnoreCase("CATEGORY") || select.equalsIgnoreCase("Kategorie") || select.equalsIgnoreCase("Categorie")){
            list = dbContextMovie.findAllMovies(dbContextMovie.createFindByCategoryQuery(got));
        }
        else if(select.equalsIgnoreCase("Name") || select.equalsIgnoreCase("Nume")) {
            Movie movie = dbContextMovie.findMovie(dbContextMovie.createFindQueryByName(got));
            list.add(movie);
        }
        else {
            list = dbContextMovie.findAllMovies(dbContextMovie.createFindByYearQuery(Integer.parseInt(got)));
        }

        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
