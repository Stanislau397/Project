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
import java.util.Optional;

public class RatingDaoImpl implements RatingDao {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);

    @Override
    public boolean add(Rating rating) throws DaoException {
        boolean isRated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_RATING)) {
            statement.setLong(1, rating.getMovie().getMovieId());
            statement.setLong(2, rating.getUser().getUserId());
            statement.setInt(3, rating.getScore());
            statement.setTimestamp(4, Timestamp.valueOf(rating.getCreatedAt()));
            int result = statement.executeUpdate();
            isRated = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRated;
    }

    @Override
    public int countAverageRatingOfMovieByUserId(long userId) throws DaoException {
        int average = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_AVERAGE_RATING_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                average = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return average;
    }

    @Override
    public boolean deleteById(long ratingId) throws DaoException {
        boolean isRatingRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_RATING_BY_ID)) {
            statement.setLong(1, ratingId);
            int result = statement.executeUpdate();
            isRatingRemoved = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRatingRemoved;
    }

    @Override
    public boolean ratingExistsByUserIdAndMovieId(long userId, long movieId) throws DaoException {
        boolean isVoted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHECK_IF_USER_RATED_MOVIE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, userId);
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
    public Optional<Rating> findPersonalUserScoreForMovie(long userId, long movieId) throws DaoException {
        Optional<Rating> isFound = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_SCORE_FOR_USER)) {
            statement.setLong(1, userId);
            statement.setLong(2, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Rating rating = Rating.newRatingBuilder()
                        .withRatingId(resultSet.getLong(TableColumn.RATING_ID))
                        .withScore(resultSet.getInt(TableColumn.USER_SCORE))
                        .build();
                isFound = Optional.of(rating);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public int countPositiveMovieScoresForUser(long userId) throws DaoException {
        int countPositive = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_POSITIVE_SCORES_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countPositive = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countPositive;
    }

    @Override
    public int countMixedMovieScoresForUser(long userId) throws DaoException {
        int countMixed = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_MIXED_SCORES_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countMixed = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countMixed;
    }

    @Override
    public int countNegativeMovieScoresForUser(long userId) throws DaoException {
        int countNegative = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_NEGATIVE_SCORES_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countNegative = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countNegative;
    }

    @Override
    public int countAllMovieScoresForUser(long userId) throws DaoException {
        int amountOfReviews = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.COUNT_MOVIE_SCORES_FOR_USER)) {
            statement.setLong(1, userId);
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
}
