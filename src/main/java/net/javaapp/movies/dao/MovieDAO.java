package net.javaapp.movies.dao;

import net.javaapp.movies.model.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private String dbURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String dbUserName = "root";
    private String dbPasswd = "";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";

    private static final String INSERT_MOVIE_SQL = "INSERT INTO movies"
            + "(title, director, price) VALUES "
            + "(?, ?, ?);";
    private static final String SELECT_ALL_MOVIES_SQL = "SELECT * FROM movies;";
    private static final String SELECT_MOVIE_BY_ID_SQL = "SELECT * FROM movies WHERE id=?;";
    private static final String UPDATE_MOVIE_SQL = "UPDATE movies SET title=?, director=?, price=? WHERE id=?;";
    private static final String DELETE_MOVIE_SQL = "DELETE FROM movies WHERE id=?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPasswd);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

    public boolean insertMovie(Movie movie) {
        boolean recordSaved = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MOVIE_SQL);
            preparedStatement.setString(1 , movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            preparedStatement.setFloat(3, movie.getPrice());
            recordSaved = preparedStatement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return recordSaved;
    }

    public Movie selectMovieById(int id) {
        Movie movie = null;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MOVIE_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                Float price = resultSet.getFloat("price");
                movie = new Movie(id,title, director,price);
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movie;
    }

    public List<Movie> selectAllMovies() {
        List<Movie> movies = new ArrayList<>();

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MOVIES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String director = resultSet.getString("director");
                Float price = resultSet.getFloat("price");
                movies.add(new Movie(id, title, director, price));
            }
            resultSet.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    public boolean deleteMovie(int id) {
        boolean recordDeleted = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE_SQL);
            preparedStatement.setInt(1, id);
            recordDeleted = preparedStatement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recordDeleted;
    }

    public boolean updateMovie(Movie movie) {
        boolean recordUpdated = false;

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MOVIE_SQL);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDirector());
            preparedStatement.setFloat(3, movie.getPrice());
            preparedStatement.setInt(4, movie.getId());
            recordUpdated = preparedStatement.executeUpdate() > 0;
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return recordUpdated;
    }
}

