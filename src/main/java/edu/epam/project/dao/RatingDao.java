package edu.epam.project.dao;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface RatingDao {

    int countPositiveMovieRatingByUserName(String userName) throws DaoException;

    int countMixedMovieRatingByUserName(String userName) throws DaoException;

    int countNegativeMovieRatingByUserName(String userName) throws DaoException;

    int countAverageMovieRatingOfUser(String userName) throws DaoException;

    int countAmountOfUserScoresByUserName(String userName) throws DaoException;

    Optional<Rating> findLatestHighScoreByUserName(String userName) throws DaoException;

    Optional<Rating> findLatestLowScoreByUserName(String userName) throws DaoException;

    boolean rateMovie(long movieId, String userName, int score) throws DaoException;

    boolean isUserAlreadyVoted(String userName, long movieId) throws DaoException;

    int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws DaoException;
}
