package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Rating;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RatingDaoImpl implements RatingDao {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);

    @Override
    public int countAverageMovieRatingOfUser(String userName) throws DaoException {
        int averageUserMovieRating = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_AVERAGE_RATING_OF_USER)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                averageUserMovieRating = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return averageUserMovieRating;
    }

    @Override
    public int countAmountOfUserScoresByUserName(String userName) throws DaoException {
        int amountOfReviews = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_AMOUNT_OF_REVIEWS)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                amountOfReviews = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return amountOfReviews;
    }

    @Override
    public boolean rateMovie(long movieId, String userName, int score) throws DaoException {
        boolean isRated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.RATE_MOVIE)) {
            statement.setLong(1, movieId);
            statement.setString(2, userName);
            statement.setInt(3, score);
            int result = statement.executeUpdate();
            isRated = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRated;
    }

    @Override
    public boolean isUserAlreadyVoted(String userName, long movieId) throws DaoException {
        boolean isVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_USER_IN_RATING)) {
            statement.setLong(1, movieId);
            statement.setString(2, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isVoted = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isVoted;
    }

    @Override
    public boolean removeRatingByUserNameAndMovieId(String userName, long movieId) throws DaoException {
        boolean isRatingRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_RATING)) {
            statement.setLong(1, movieId);
            statement.setString(2, userName);
            int result = statement.executeUpdate();
            isRatingRemoved = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRatingRemoved;
    }

    @Override
    public int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws DaoException {
        int score = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_SCORE)) {
            statement.setString(1, userName);
            statement.setLong(2, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                score = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return score;
    }
}
