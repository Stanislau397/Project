package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieDaoImpl implements MovieDao {

    public static final Logger logger = LogManager.getLogger(MovieDaoImpl.class);

    @Override
    public boolean add(Movie movie) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_MOVIE)) {
            statement.setDouble(1, movie.getMovie_id());
            statement.setString(2, movie.getTitle());
            statement.setDate(3, movie.getReleaseDate());
            statement.setInt(4, movie.getRunTime());
            statement.setString(5, movie.getCountry());
            statement.setString(6, movie.getDescription());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteMovieByTitle(String title) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BY_TITLE)) {
            statement.setString(1, title);
            int update = statement.executeUpdate();
            isDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        List<Movie> allMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setMovie_id(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                allMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allMovies;
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) throws DaoException {
        Optional<Movie> isFound = Optional.empty();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Movie movie = new Movie();
            movie.setMovie_id(resultSet.getLong(TableColumn.MOVIE_ID));
            movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
            movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
            movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
            movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
            movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
            isFound = Optional.of(movie);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }
}
