package edu.epam.project.service;

import edu.epam.project.exception.ServiceException;

public interface RatingService {

    int countAverageMovieRatingOfUser(String userName) throws ServiceException;

    boolean rateMovie(long movieId, String userName, int score) throws ServiceException;

    boolean isUserAlreadyVoted(String userName, long movieId) throws ServiceException;

    boolean removeRatingByUserNameAndMovieId(String userName, long movieId) throws ServiceException;

    int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws ServiceException;

    int countPositiveMovieScores(String userName) throws ServiceException;

    int countMixedMovieScores(String userName) throws ServiceException;

    int countNegativeMovieScores(String userName) throws ServiceException;

    int countAllMovieScores(String userName) throws ServiceException;
}
