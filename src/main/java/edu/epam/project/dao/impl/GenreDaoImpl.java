package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.GenreDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Genre;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao {

    private static final Logger logger = LogManager.getLogger(GenreDaoImpl.class);

    @Override
    public boolean add(Genre genre) throws DaoException {
        boolean isAdded;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_GENRE)) {
            statement.setLong(1, genre.getGenreId());
            statement.setString(2, genre.getTitle());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean removeById(long genreId) throws DaoException {
        boolean isRemoved;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_FROM_GENRE)) {
            statement.setLong(1, genreId);
            int update = statement.executeUpdate();
            isRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRemoved;
    }

    @Override
    public boolean addGenreToMovieById(long genreId, long movieId) throws DaoException {
        boolean isAdded;
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_MOVIE_GENRE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, genreId);
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public Optional<Genre> findMovieGenreByMovieId(long movieId) throws DaoException {
        Optional<Genre> isFound = Optional.empty();
        try(Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_GENRE_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long genreId = resultSet.getLong(TableColumn.GENRE_ID);
                String genreTitle = resultSet.getString(TableColumn.GENRE_TITLE);
                Genre genre = new Genre(genreId, genreTitle);
                isFound = Optional.of(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }
}
