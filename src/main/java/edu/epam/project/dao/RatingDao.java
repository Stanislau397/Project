package edu.epam.project.dao;

import edu.epam.project.exception.DaoException;

public interface RatingDao {

    int countAverageMovieRating(long movieId) throws DaoException;

    boolean rateMovie(long movieId, String userName, int score) throws DaoException;
}
