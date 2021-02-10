package edu.epam.project.sevice.impl;

import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.impl.RatingDaoImpl;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.RatingService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LogManager.getLogger(RatingDaoImpl.class);
    private RatingDao ratingDao = new RatingDaoImpl();

    @Override
    public double countAverageMovieRating(long movieId) throws ServiceException {
        double average;
        try {
            average = ratingDao.countAverageMovieRating(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return average;
    }

    @Override
    public boolean rateMovie(long movieId, long userId, double score) throws ServiceException {
        boolean isRated;
        try {
            isRated = ratingDao.rateMovie(movieId, userId, score);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRated;
    }
}
