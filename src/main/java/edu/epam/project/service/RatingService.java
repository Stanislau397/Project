package edu.epam.project.service;

import edu.epam.project.entity.Rating;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface RatingService {

    int countAverageMovieRating(long movieId) throws ServiceException;

    int countPositiveMovieRatingByUserName(String userName) throws ServiceException;

    int countMixedMovieRatingByUserName(String userName) throws ServiceException;

    int countNegativeMovieRatingByUserName(String userName) throws ServiceException;

    int countAverageMovieRatingOfUser(String userName) throws ServiceException;

    int countAmountOfUserScoresByUserName(String userName) throws ServiceException;

    Optional<Rating> findLatestHighScoreByUserName(String userName) throws ServiceException;

    Optional<Rating> findLatestLowScoreByUserName(String userName) throws ServiceException;

    boolean rateMovie(long movieId, String userName, int score) throws ServiceException;

    boolean isUserAlreadyVoted(String userName, long movieId) throws ServiceException;

    int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws ServiceException;

    List<Rating> findAllRatedMoviesByUserName(String userName) throws ServiceException;
}
