package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;
import model.DBContextUser;
import model.Movie;
import model.User;

public class ReadMovieByIDCommand implements ICommand{
        private static final DBContextMovie dbContextMovie = new DBContextMovie();
        private static final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String execute(String data) {
            Movie movie = new Movie();
            try {
                String query = dbContextMovie.createFindQueryByID(Integer.parseInt(data));
                movie = dbContextMovie.findMovie(query);

                return objectMapper.writeValueAsString(movie);
            } catch (JsonMappingException ex) {
                ex.printStackTrace();
                return "";
            } catch (JsonProcessingException ex) {
                ex.printStackTrace();
                return "";
            }
        }
}
