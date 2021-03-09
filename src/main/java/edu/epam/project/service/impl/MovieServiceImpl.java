package edu.epam.project.service.impl;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    public static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private MovieDao movieDao = new MovieDaoImpl();

    @Override
    public boolean add(Movie movie) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = movieDao.add(movie);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteMovieByTitle(String title) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = movieDao.deleteMovieByTitle(title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) throws ServiceException {
        Optional<Movie> isFound;
        try {
            isFound = movieDao.findMovieByTitle(title);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public List<Movie> findAllMovies() throws ServiceException {
        List<Movie> allMovies;
        try {
            allMovies = movieDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allMovies;
    }

    @Override
    public Optional<Movie> findMovieById(long movieId) throws ServiceException {
        Optional<Movie> isFound;
        try {
            isFound = movieDao.findMovieById(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public List<Movie> findRatedMoviesByUserName(String userName) throws ServiceException {
        List<Movie> ratedMovies;
        try {
            ratedMovies = movieDao.findRatedMoviesByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return ratedMovies;
    }

    @Override
    public boolean addActor(Actor actor) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = movieDao.addActor(actor);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean removeActorByFirstName(String firstName) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = movieDao.removeActorByFirstName(firstName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
    }

    @Override
    public List<Actor> findActorsByMovieId(long movieId) throws ServiceException {
        List<Actor> actors;
        try {
            actors = movieDao.findActorsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actors;
    }

    @Override
    public boolean addDirector(Director director) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = movieDao.addDirector(director);
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
            directors = movieDao.findDirectorsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directors;
    }
}
