package edu.epam.project.sevice.impl;

import edu.epam.project.dao.GenreDao;
import edu.epam.project.dao.impl.GenreDaoImpl;
import edu.epam.project.entity.Genre;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.GenreService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LogManager.getLogger(GenreServiceImpl.class);
    private GenreDao genreDao = new GenreDaoImpl();

    @Override
    public boolean add(Genre genre) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = genreDao.add(genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean removeById(long genreId) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = genreDao.removeById(genreId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
    }

    @Override
    public boolean addGenreToMovieById(long genreId, long movieId) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = genreDao.addGenreToMovieById(genreId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public Optional<Genre> findMovieGenreByMovieId(long movieId) throws ServiceException {
        Optional<Genre> isFound;
        try {
            isFound = genreDao.findMovieGenreByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }
}
