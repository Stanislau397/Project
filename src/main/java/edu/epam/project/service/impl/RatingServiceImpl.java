package edu.epam.project.service.impl;

import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.impl.RatingDaoImpl;
import edu.epam.project.entity.Rating;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);
    private RatingDao ratingDao = new RatingDaoImpl();

    @Override
    public int countPositiveMovieRatingByUserName(String userName) throws ServiceException {
        int positiveReviews;
        try {
            positiveReviews = ratingDao.countPositiveMovieRatingByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return positiveReviews;
    }

    @Override
    public int countMixedMovieRatingByUserName(String userName) throws ServiceException {
        int mixedReviews;
        try {
            mixedReviews = ratingDao.countMixedMovieRatingByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return mixedReviews;
    }

    @Override
    public int countNegativeMovieRatingByUserName(String userName) throws ServiceException {
        int negativeReviews;
        try {
            negativeReviews = ratingDao.countNegativeMovieRatingByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return negativeReviews;
    }

    @Override
    public int countAverageMovieRatingOfUser(String userName) throws ServiceException {
        int averageUserMovieRating;
        try {
            averageUserMovieRating = ratingDao.countAverageMovieRatingOfUser(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return averageUserMovieRating;
    }

    @Override
    public int countAmountOfUserScoresByUserName(String userName) throws ServiceException {
        int amountOfReviews;
        try {
            amountOfReviews = ratingDao.countAmountOfUserScoresByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfReviews;
    }

    @Override
    public Optional<Rating> findLatestHighScoreByUserName(String userName) throws ServiceException {
        Optional<Rating> latestHighScore;
        try {
            latestHighScore = ratingDao.findLatestHighScoreByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestHighScore;
    }

    @Override
    public Optional<Rating> findLatestLowScoreByUserName(String userName) throws ServiceException {
        Optional<Rating> latestLowScore;
        try {
            latestLowScore = ratingDao.findLatestLowScoreByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestLowScore;
    }

    @Override
    public boolean rateMovie(long movieId, String userName, int score) throws ServiceException {
        boolean isRated;
        try {
            isRated = ratingDao.rateMovie(movieId, userName, score);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRated;
    }

    @Override
    public boolean isUserAlreadyVoted(String userName, long movieId) throws ServiceException {
        boolean isVoted;
        try {
            isVoted = ratingDao.isUserAlreadyVoted(userName, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isVoted;
    }

    @Override
    public int findMovieScoreByUserNameAndMovieId(String userName, long movieId) throws ServiceException {
        int score;
        try {
            score = ratingDao.findMovieScoreByUserNameAndMovieId(userName, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return score;
    }
}
