package edu.epam.project.sevice;

import edu.epam.project.exception.ServiceException;

public interface RatingService {

    int countAverageMovieRating(long movieId) throws ServiceException;
    boolean rateMovie(long movieId, String userName, int score) throws ServiceException;
}
