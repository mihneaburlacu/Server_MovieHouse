package model;

import model.Enums.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBContextMovie {
    protected static final Logger LOGGER = Logger.getLogger(DBContextMovie.class.getName());

    public DBContextMovie() {}

    public String createInsertQuery(Movie movie) {
        String ID = "'" + movie.getID() + "'";
        String name = "'" + movie.getName() + "'";
        String type = "'" + movie.getType().toString() + "'";
        String category = "'" + movie.getCategory() + "'";
        String year = "'" + movie.getYear() + "'";
        String id_creator = "'" + movie.getIdCreator() + "'";

        String query = "INSERT INTO movie VALUES( " +  ID + ", " + name + ", " + type + ", " + category + ", " + year + ", " + id_creator + ")";

        return query;
    }

    public String createDeleteQueryByID(int id) {
        String query = "DELETE FROM movie WHERE id = " + id;

        return query;
    }

    public String createDeleteQueryByName(String name) {
        String query = "DELETE FROM movie WHERE name = '" + name + "'";

        return query;
    }

    public String createFindQueryByID(int id) {
        String query = "SELECT id, name, type, category, year, id_creator FROM movie WHERE id = " + id;

        return query;
    }

    public String createFindQueryByName(String name) {
        return "SELECT id, name, type, category, year, id_creator FROM movie WHERE name = '" + name + "'";
    }

    public String createFindAllQuery() {
        return "SELECT id, name, type, category, year, id_creator FROM movie";
    }

    public String createFindByTypeQuery(String type) {
        return "SELECT id, name, type, category, year, id_creator FROM movie WHERE type = '" + type.toUpperCase() + "'";
    }

    public String createFindByCategoryQuery(String category) {
        return "SELECT id, name, type, category, year, id_creator FROM movie WHERE category = '" + category + "'";
    }

    public String createFindByYearQuery(int year) {
        return "SELECT id, name, type, category, year, id_creator FROM movie WHERE year = " + year;
    }

    public String createUpdateQuery(int id, String name, String type, String category, int year) {
        String query = "UPDATE movie SET name = '" + name + "', type = '" + type.toUpperCase() + "', category = '" + category +
                "', year = " + year + " WHERE id = " + id;

        return query;
    }

    public void insert(Movie movie) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement statement = null;
        String query = createInsertQuery(movie);
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Movie: insert " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }
    }

    public void deleteMovie(String query) {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement statement = null;
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Movie:delete " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, null);
        }
    }

    public Movie findMovie(String query) {
        Connection connection = ConnectionFactory.getConnection();

        Movie movie = null;

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        System.out.println(query);

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String type = resultSet.getString("type");
                if(type.equals("ARTISTIC")) {
                    movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"), Type.ARTISTIC, resultSet.getString("category"), resultSet.getInt("year"), resultSet.getInt("id_creator"));
                }
                else {
                    movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"), Type.SERIES, resultSet.getString("category"), resultSet.getInt("year"), resultSet.getInt("id_creator"));
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Movie:find " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
            return movie;
        }
    }

    public List<Movie> findAllMovies(String query) {
        Connection connection = ConnectionFactory.getConnection();

        List<Movie> movieList = new ArrayList<>();

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        System.out.println(query);

        try {
            Movie movie  = null;

            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String type = resultSet.getString("type");
                if(type.equals("ARTISTIC")) {
                    movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"), Type.ARTISTIC, resultSet.getString("category"), resultSet.getInt("year"), resultSet.getInt("id_creator"));
                }
                else {
                    movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"), Type.SERIES, resultSet.getString("category"), resultSet.getInt("year"), resultSet.getInt("id_creator"));
                }

                movieList.add(movie);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Movie:find " + e.getMessage());
        } finally {
            ConnectionFactory.closeAll(connection, statement, resultSet);
            return movieList;
        }
    }

    public void updateMovie(int id, String name, String type, String category, int year) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(id, name, type, category, year);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Movie:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

