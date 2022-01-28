package edu.epam.project.service;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.ServiceException;

public interface RatingService {

    int countAverageMovieRatingForUser(long userId) throws ServiceException;

    boolean add(long movieId, long userId, int score) throws ServiceException;

    boolean isUserRatedMovie(long userId, long movieId) throws ServiceException;

    boolean deleteById(long ratingId) throws ServiceException;

    Rating findPersonalUserScoreForMovie(long userId, long movieId) throws ServiceException;

    int countPositiveMovieScoresForUser(long userId) throws ServiceException;

    int countMixedMovieScoresForUser(long userId) throws ServiceException;

    int countNegativeMovieScoresForUser(long userId) throws ServiceException;

    int countAllMovieScoresForUser(long userId) throws ServiceException;
}
