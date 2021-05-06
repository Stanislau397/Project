package edu.epam.project.service.impl;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
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
    public boolean addGenre(Genre genre) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = movieDao.addGenre(genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addGenreToMovie(long genreId, long movieId) throws ServiceException {
        boolean isGenreAdded;
        try {
            isGenreAdded = movieDao.addGenreToMovie(genreId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreAdded;
    }

    @Override
    public List<Genre> findAllGenres() throws ServiceException {
        List<Genre> genres;
        try {
            genres = movieDao.findAllGenres();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return genres;
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
    public List<Movie> findAllCurrentYearMovies() throws ServiceException {
        List<Movie> currentYearMovies;
        try {
            currentYearMovies = movieDao.findAllCurrentYearMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return currentYearMovies;
    }

    @Override
    public List<Movie> findMoviesByYear(int year) throws ServiceException {
        List<Movie> moviesByYear;
        try {
            moviesByYear = movieDao.findMoviesByYear(year);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesByYear;
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre) throws ServiceException {
        List<Movie> moviesByGenre;
        try {
            moviesByGenre = movieDao.findMoviesByGenre(genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesByGenre;
    }

    @Override
    public List<Movie> findMoviesByGenreAndYear(Genre genre, int year) throws ServiceException {
        List<Movie> moviesByGenreAndYear;
        try {
            moviesByGenreAndYear = movieDao.findMoviesByGenre(genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesByGenreAndYear;
    }

    @Override
    public List<Movie> findNewestMovies() throws ServiceException {
        List<Movie> newestMovies;
        try {
            newestMovies = movieDao.findNewestMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return newestMovies;
    }

    @Override
    public List<Movie> findMostRatedMovies() throws ServiceException {
        List<Movie> mostRatedMovies;
        try {
            mostRatedMovies = movieDao.findMostRatedMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return mostRatedMovies;
    }

    @Override
    public List<Integer> findAllMovieYears() throws ServiceException {
        List<Integer> movieYears;
        try {
            movieYears = movieDao.findAllMovieYears();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movieYears;
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
    public List<Movie> findMoviesByKeyWord(String keyWord) throws ServiceException {
        List<Movie> moviesByKeyWord;
        try {
            moviesByKeyWord = movieDao.findMoviesByKeyWord(keyWord);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesByKeyWord;
    }

    @Override
    public boolean addActor(Actor actor) throws ServiceException {
        boolean isAdded = false;
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();
        try {
            boolean actorExist = isActorAlreadyExists(firstName, lastName);
            if (!actorExist) {
                isAdded = movieDao.addActor(actor);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addActorToMovieByMovieId(Actor actor, long movieId) throws ServiceException {
        boolean isActorAddedToMovie = false;
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();
        try {
            Optional<Actor> actorOptional = findActorByFirstLastName(firstName, lastName);
            if (actorOptional.isPresent()) {
                actor = actorOptional.get();
                isActorAddedToMovie = movieDao.addActorToMovieByMovieId(actor, movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorAddedToMovie;
    }

    @Override
    public boolean removeActorFromMovieById(long actorId, long movieId) throws ServiceException {
        boolean isActorDeleted;
        try {
            isActorDeleted = movieDao.removeActorFromMovieById(actorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorDeleted;
    }

    @Override
    public boolean isActorAlreadyExists(String firstName, String lastName) throws ServiceException {
        boolean isExists;
        try {
            isExists = movieDao.isActorAlreadyExists(firstName, lastName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isExists;
    }

    @Override
    public Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws ServiceException {
        Optional<Actor> isFound;
        try {
            isFound = movieDao.findActorByFirstLastName(firstName, lastName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
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
        boolean isAdded = false;
        boolean directorExists;
        try {
            directorExists = isDirectorAlreadyExists(director);
            if (!directorExists) {
                isAdded = movieDao.addDirector(director);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addDirectorToMovieByMovieId(Director director, long movieId) throws ServiceException {
        boolean isDirectorAdded = false;
        String firstName = director.getFirstName();
        String lastName = director.getLastName();
        try {
            Optional<Director> directorOptional = findDirectorByFirstLastName(firstName, lastName);
            if (directorOptional.isPresent()) {
                director = directorOptional.get();
                isDirectorAdded = movieDao.addDirectorToMovieByMovieId(director, movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDirectorAdded;
    }

    @Override
    public boolean removeDirectorFromMovie(long directorId, long movieId) throws ServiceException {
        boolean isDirectorRemoved;
        try {
            isDirectorRemoved = movieDao.removeDirectorFromMovie(directorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDirectorRemoved;
    }

    @Override
    public Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws ServiceException {
        Optional<Director> directorOptional;
        try {
            directorOptional = movieDao.findDirectorByFirstLastName(firstName, lastName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directorOptional;
    }

    @Override
    public boolean isDirectorAlreadyExists(Director director) throws ServiceException {
        boolean isDirectorExists;
        try {
            isDirectorExists = movieDao.isDirectorAlreadyExists(director);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDirectorExists;
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
