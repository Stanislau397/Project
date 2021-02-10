package edu.epam.project.dao;

import edu.epam.project.exception.DaoException;

public interface RatingDao {

    double countAverageMovieRating(long movieId) throws DaoException;
    boolean rateMovie(long movieId, long userId, double score) throws DaoException;
}
