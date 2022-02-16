package edu.epam.project.dao;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.DaoException;

import java.util.Optional;

public interface RatingDao {

    boolean add(Rating rating) throws DaoException; //ok

    boolean deleteById(long ratingId) throws DaoException; //ok

    boolean ratingExistsByUserIdAndMovieId(long userId, long movieId) throws DaoException; //ok

    int countAverageRatingOfMovieByUserId(long userId) throws DaoException; //ok

    Optional<Rating> findPersonalUserScoreForMovie(long userId, long movieId) throws DaoException; //ok

    int countPositiveMovieScoresForUser(long userId) throws DaoException; //ok

    int countMixedMovieScoresForUser(long userId) throws DaoException; //ok

    int countNegativeMovieScoresForUser(long userId) throws DaoException; //ok

    int countAllMovieScoresForUser(long userId) throws DaoException; //ok
}
