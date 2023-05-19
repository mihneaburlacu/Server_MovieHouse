package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;
import model.DBContextUser;
import model.Movie;
import model.User;

import java.util.List;

public class ViewAllMoviesCommand implements ICommand{
    private static final DBContextMovie dbContextMovie = new DBContextMovie();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        String query = dbContextMovie.createFindAllQuery();
        List<Movie> movies = dbContextMovie.findAllMovies(query);

        try {
            return objectMapper.writeValueAsString(movies);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
