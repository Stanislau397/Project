package edu.epam.project.dao;

import edu.epam.project.exception.DaoException;

public interface RatingDao {

    int countAverageMovieRatingOfUser(String userName) throws DaoException;

    boolean rateMovie(long movieId, String userName, int score) throws DaoException;

    boolean isUserAlreadyVoted(String userName, long movieId) throws DaoException;

    boolean removeRatingByUserNameAndMovieId(String userName, long movieId) throws DaoException;

    int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws DaoException;

    int countPositiveMovieScores(String userName) throws DaoException;

    int countMixedMovieScores(String userName) throws DaoException;

    int countNegativeMovieScores(String userName) throws DaoException;

    int countAllMovieScores(String userName) throws DaoException;
}
