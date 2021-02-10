package edu.epam.project.sevice;

import edu.epam.project.exception.ServiceException;

public interface RatingService {

    double countAverageMovieRating(long movieId) throws ServiceException;
    boolean rateMovie(long movieId, long userId, double score) throws ServiceException;
}
