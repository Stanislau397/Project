package edu.epam.project.service.impl;

import edu.epam.project.dao.DirectorDao;
import edu.epam.project.dao.impl.DirectorDaoIml;
import edu.epam.project.entity.Director;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.DirectorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DirectorServiceImpl implements DirectorService {

    private static final Logger logger = LogManager.getLogger(DirectorServiceImpl.class);
    private DirectorDao directorDao = new DirectorDaoIml();

    @Override
    public boolean add(Director director) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = directorDao.add(director);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public List<Director> findDirectorsByMovieId(long movieId) throws ServiceException {
        List<Director> directors;
        try {
            directors = directorDao.findDirectorsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directors;
    }
}
