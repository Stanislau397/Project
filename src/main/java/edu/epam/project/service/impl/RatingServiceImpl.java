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

import java.util.Optional;

public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);
    private RatingDao ratingDao = new RatingDaoImpl();

    @Override
    public boolean add(Rating rating) throws ServiceException {
        boolean isRated;
        try {
            isRated = ratingDao.add(rating);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRated;
    }

    @Override
    public boolean ratingExistsByUserIdAndMovieId(long userId, long movieId) throws ServiceException {
        boolean isVoted;
        try {
            isVoted = ratingDao.ratingExistsByUserIdAndMovieId(userId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isVoted;
    }

    @Override
    public boolean deleteById(long ratingId) throws ServiceException {
        boolean isRatingRemoved;
        try {
            isRatingRemoved = ratingDao.deleteById(ratingId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRatingRemoved;
    }

    @Override
    public Rating findPersonalUserScoreForMovie(long userId, long movieId) throws ServiceException {
        Optional<Rating> isFound;
        Rating movieRatingForUser = Rating.newRatingBuilder().build();
        try {
            isFound = ratingDao.findPersonalUserScoreForMovie(userId, movieId);
            if (isFound.isPresent()) {
                movieRatingForUser = isFound.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movieRatingForUser;
    }

    @Override
    public int countPositiveMovieScoresForUser(long userId) throws ServiceException {
        int countPositive;
        try {
            countPositive = ratingDao.countPositiveMovieScoresForUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countPositive;
    }

    @Override
    public int countMixedMovieScoresForUser(long userId) throws ServiceException {
        int countMixed;
        try {
            countMixed = ratingDao.countMixedMovieScoresForUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countMixed;
    }

    @Override
    public int countNegativeMovieScoresForUser(long userId) throws ServiceException {
        int countNegative;
        try {
            countNegative = ratingDao.countNegativeMovieScoresForUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countNegative;
    }

    @Override
    public int countAverageRatingOfMovieByUserId(long userId) throws ServiceException {
        int averageUserMovieRating;
        try {
            averageUserMovieRating = ratingDao.countAverageRatingOfMovieByUserId(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return averageUserMovieRating;
    }

    @Override
    public int countAllMovieScoresForUser(long userId) throws ServiceException {
        int countAll;
        try {
            countAll = ratingDao.countAllMovieScoresForUser(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countAll;
    }
}
