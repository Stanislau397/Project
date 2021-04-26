package edu.epam.project.service;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.ServiceException;

import java.util.Optional;

public interface RatingService {

    int countAverageMovieRatingOfUser(String userName) throws ServiceException;

    int countAmountOfUserScoresByUserName(String userName) throws ServiceException;

    Optional<Rating> findLatestHighScoreByUserName(String userName) throws ServiceException;

    Optional<Rating> findLatestLowScoreByUserName(String userName) throws ServiceException;

    boolean rateMovie(long movieId, String userName, int score) throws ServiceException;

    boolean isUserAlreadyVoted(String userName, long movieId) throws ServiceException;

    public boolean removeRatingByUserNameAndMovieId(String userName, long movieId) throws ServiceException;

    int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws ServiceException;
}
