package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;
import model.DBContextUser;
import model.Movie;
import model.User;

public class AddMovieCommand implements ICommand{
    private static final DBContextMovie dbContextMovie = new DBContextMovie();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        Movie movie = new Movie();
        try {
            movie = objectMapper.reader().forType(Movie.class).readValue(data);
            dbContextMovie.insert(movie);
            return "movie was added";
        } catch (JsonMappingException ex) {
            ex.printStackTrace();
            return "";
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
