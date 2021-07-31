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
    public boolean removeRatingByUserNameAndMovieId(String userName, long movieId) throws ServiceException {
        boolean isRatingRemoved;
        try {
            isRatingRemoved = ratingDao.removeRatingByUserNameAndMovieId(userName, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRatingRemoved;
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

    @Override
    public int countPositiveMovieScores(String userName) throws ServiceException {
        int countPositive;
        try {
            countPositive = ratingDao.countPositiveMovieScores(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countPositive;
    }

    @Override
    public int countMixedMovieScores(String userName) throws ServiceException {
        int countMixed;
        try {
            countMixed = ratingDao.countMixedMovieScores(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countMixed;
    }

    @Override
    public int countNegativeMovieScores(String userName) throws ServiceException {
        int countNegative;
        try {
            countNegative = ratingDao.countNegativeMovieScores(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countNegative;
    }

    @Override
    public int countAllMovieScores(String userName) throws ServiceException {
        int countAll;
        try {
            countAll = ratingDao.countAllMovieScores(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countAll;
    }
}
