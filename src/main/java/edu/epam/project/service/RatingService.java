package edu.epam.project.service;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.ServiceException;

public interface RatingService {

    boolean add(Rating rating) throws ServiceException;

    boolean ratingExistsByUserIdAndMovieId(long userId, long movieId) throws ServiceException;

    boolean deleteById(long ratingId) throws ServiceException;

    Rating findPersonalUserScoreForMovie(long userId, long movieId) throws ServiceException;

    int countPositiveMovieScoresForUser(long userId) throws ServiceException;

    int countMixedMovieScoresForUser(long userId) throws ServiceException;

    int countNegativeMovieScoresForUser(long userId) throws ServiceException;

    int countAllMovieScoresForUser(long userId) throws ServiceException;

    int countAverageRatingOfMovieByUserId(long userId) throws ServiceException;
}
