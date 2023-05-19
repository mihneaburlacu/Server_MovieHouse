package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DBContextMovie;
import model.Enums.Type;
import model.Movie;

public class UpdateMovieCommand implements ICommand{
    private static final DBContextMovie dbContextMovie = new DBContextMovie();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String execute(String data) {
        String[] split = data.split(",");
        try {
            dbContextMovie.updateMovie(Integer.parseInt(split[0]), split[1], split[2], split[3], Integer.parseInt(split[4]));

            Movie movie = new Movie();
            if(split[2].equals("SERIES")){
                movie = new Movie(Integer.parseInt(split[0]), split[1], Type.SERIES, split[3], Integer.parseInt(split[4]), 10);
            }
            else {
                movie = new Movie(Integer.parseInt(split[0]), split[1], Type.ARTISTIC, split[3], Integer.parseInt(split[4]), 10);
            }

            return objectMapper.writeValueAsString(movie);
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }

    }
}
