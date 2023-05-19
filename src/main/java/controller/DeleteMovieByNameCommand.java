package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;

public class DeleteMovieByNameCommand implements ICommand{
    private static final DBContextMovie dbContextMovie = new DBContextMovie();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        try {
            String query = dbContextMovie.createDeleteQueryByName(data);
            dbContextMovie.deleteMovie(query);

            return "deleted";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
