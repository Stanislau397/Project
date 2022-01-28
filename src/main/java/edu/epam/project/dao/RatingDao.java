package edu.epam.project.dao;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.DaoException;

import java.util.Optional;

public interface RatingDao {

    boolean add(long movieId, long userId, int score) throws DaoException; //ok

    boolean deleteById(long ratingId) throws DaoException; //ok

    boolean isUserRatedMovie(long userId, long movieId) throws DaoException; //ok

    int countAverageMovieRatingForUser(long userId) throws DaoException; //ok

    Optional<Rating> findPersonalUserScoreForMovie(long userId, long movieId) throws DaoException; //ok

    int countPositiveMovieScoresForUser(long userId) throws DaoException; //ok

    int countMixedMovieScoresForUser(long userId) throws DaoException; //ok

    int countNegativeMovieScoresForUser(long userId) throws DaoException; //ok

    int countAllMovieScoresForUser(long userId) throws DaoException; //ok
}
