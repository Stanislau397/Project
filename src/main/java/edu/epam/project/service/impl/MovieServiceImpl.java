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

import java.sql.Date;
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
    public boolean updateMoviePosterByMovieId(String picturePath, long movieId) throws ServiceException {
        boolean isPosterUpdated;
        try {
            isPosterUpdated = movieDao.updateMoviePosterByMovieId(picturePath, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isPosterUpdated;
    }

    @Override
    public boolean updateTitleRunTimeReleaseDateDescriptionByMovieId(String title, int runTime, Date releaseDate, String description, long movie_id)
            throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateTitleRunTimeReleaseDateDescriptionByMovieId(title, runTime, releaseDate, description, movie_id);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateMovieTrailerByMovieId(long movieId, String trailer) throws ServiceException {
        boolean isTrailerUpdated;
        try {
            isTrailerUpdated = movieDao.updateMovieTrailerByMovieId(movieId, trailer);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isTrailerUpdated;
    }

    @Override
    public int countMovies() throws ServiceException {
        int counter;
        try {
            counter = movieDao.countMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return counter;
    }

    @Override
    public int countUserRatedMovies(String userName) throws ServiceException {
        int countRatedMovies;
        try {
            countRatedMovies = movieDao.countUserRatedMovies(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return countRatedMovies;
    }

    @Override
    public Optional<Movie> findLatestHighRatedMovieForUser(String userName) throws ServiceException {
        Optional<Movie> latestHighScoreMovie;
        try {
            latestHighScoreMovie = movieDao.findLatestHighRatedMovieForUser(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestHighScoreMovie;
    }

    @Override
    public Optional<Movie> findLatestLowRatedMovieForUser(String userName) throws ServiceException {
        Optional<Movie> latestLowScoreMovie;
        try {
            latestLowScoreMovie = movieDao.findLatestLowRatedMovieForUser(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestLowScoreMovie;
    }

    @Override
    public Optional<Movie> findMoviePosterByMovieId(long movieId) throws ServiceException {
        Optional<Movie> moviePoster;
        try {
            moviePoster = movieDao.findMoviePosterByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviePoster;
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
    public boolean removeGenreById(long genreId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = movieDao.removeGenreById(genreId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
    }

    @Override
    public Optional<Genre> findGenreByTitle(String genreTitle) throws ServiceException {
        Optional<Genre> isFound;
        try {
            isFound = movieDao.findGenreByTitle(genreTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
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
    public boolean removeGenreFromMovieByMovieAndGenreId(long genreId, long movieId) throws ServiceException {
        boolean isGenreRemoved;
        try {
            isGenreRemoved = movieDao.removeGenreFromMovieByMovieAndGenreId(movieId, genreId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreRemoved;
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
    public int countGenres() throws ServiceException {
        int genres;
        try {
            genres = movieDao.countGenres();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return genres;
    }

    @Override
    public int countActors() throws ServiceException {
        int actors;
        try {
            actors = movieDao.countActors();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actors;
    }

    @Override
    public int countDirectors() throws ServiceException {
        int directors;
        try {
            directors = movieDao.countDirectors();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directors;
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
    public List<Movie> findAllMovies(int page, int total) throws ServiceException {
        List<Movie> allMovies;
        try {
            allMovies = movieDao.findAll(page, total);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allMovies;
    }

    @Override
    public List<Movie> findLatestUploadedMovies() throws ServiceException {
        List<Movie> latestMovies;
        try {
            latestMovies = movieDao.findLatestUploadedMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestMovies;
    }

    @Override
    public List<Movie> findLatestReviewedMovies() throws ServiceException {
        List<Movie> latestReviewedMovies;
        try {
            latestReviewedMovies = movieDao.findLatestReviewedMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestReviewedMovies;
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
    public List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws ServiceException {
        List<Movie> moviesByGenreAndYear;
        try {
            moviesByGenreAndYear = movieDao.findMoviesByGenreAndYear(genre, year);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesByGenreAndYear;
    }

    @Override
    public List<Genre> findMovieGenresByMovieId(long movieId) throws ServiceException {
        List<Genre> movieGenres;
        try {
            movieGenres = movieDao.findMovieGenresByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movieGenres;
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
    public List<Movie> findUpcomingMovies() throws ServiceException {
        List<Movie> upcomingMovies;
        try {
            upcomingMovies = movieDao.findUpcomingMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return upcomingMovies;
    }

    @Override
    public List<Movie> findUpcomingMoviesByGenreTitle(String genreTitle) throws ServiceException {
        List<Movie> upcomingMoviesByGenre;
        try {
            upcomingMoviesByGenre = movieDao.findUpcomingMoviesByGenreTitle(genreTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return upcomingMoviesByGenre;
    }

    @Override
    public List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws ServiceException {
        List<Movie> currentYearMoviesByGenre;
        try {
            currentYearMoviesByGenre = movieDao.findCurrentYearMoviesByGenreTitle(genreTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return currentYearMoviesByGenre;
    }

    @Override
    public List<Movie> findNewestMoviesByGenreTitle(String genreTitle) throws ServiceException {
        List<Movie> newestMoviesByGenre;
        try {
            newestMoviesByGenre = movieDao.findNewestMoviesByGenreTitle(genreTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return newestMoviesByGenre;
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
    public List<Movie> findBestMoviesForActorByActorId(long actorId) throws ServiceException {
        List<Movie> bestMoviesForActor;
        try {
            bestMoviesForActor = movieDao.findBestMoviesForActorByActorId(actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return bestMoviesForActor;
    }

    @Override
    public List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws ServiceException {
        List<Movie> bestMoviesForDirector;
        try {
            bestMoviesForDirector = movieDao.findBestMoviesForDirectorByDirectorId(directorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return bestMoviesForDirector;
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
    public List<Movie> findRatedMoviesByUserName(String userName, int start, int total) throws ServiceException {
        List<Movie> ratedMovies;
        try {
            ratedMovies = movieDao.findRatedMoviesByUserName(userName, start, total);
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
    public List<Movie> findMoviesForActorByActorId(long actorId) throws ServiceException {
        List<Movie> moviesForActor;
        try {
            moviesForActor = movieDao.findMoviesForActorByActorId(actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesForActor;
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
    public boolean addActorToMovieById(long actorId, long movieId) throws ServiceException {
        boolean isActorAdded;
        try {
            isActorAdded = movieDao.addActorToMovieById(actorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorAdded;
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
    public boolean updateActorPictureByActorId(long actorId, String picture) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateActorPictureByActorId(actorId, picture);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateActorInfoByActorId(long actorId, String firstName, String lastName, Date birth_date, double height) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateActorInfoByActorId(actorId, firstName, lastName, birth_date, height);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateActorFirstAndLastNameByActorId(String firstName, String lastName, long actorId) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateActorFirstAndLastNameByActorId(firstName, lastName, actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean removeActorByActorId(long actorId) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = movieDao.removeActorByActorId(actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
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
    public Optional<Actor> findActorInfoByActorId(long actorId) throws ServiceException {
        Optional<Actor> actorInfo;
        try {
            actorInfo = movieDao.findActorInfoByActorId(actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actorInfo;
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
    public List<Actor> findAllActors() throws ServiceException {
        List<Actor> allActors;
        try {
            allActors = movieDao.findAllActors();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allActors;
    }

    @Override
    public List<Actor> findActorsByKeyWords(String keyWords) throws ServiceException {
        List<Actor> actorsByKeyWords;
        try {
            actorsByKeyWords = movieDao.findActorsByKeyWords(keyWords);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actorsByKeyWords;
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
    public Optional<Director> findDirectorInfoByDirectorId(long directorId) throws ServiceException {
        Optional<Director> directorInfo;
        try {
            directorInfo = movieDao.findDirectorInfoByDirectorId(directorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directorInfo;
    }

    @Override
    public boolean addDirectorToMovieById(long directorId, long movieId) throws ServiceException {
        boolean isDirectorAdded;
        try {
            isDirectorAdded = movieDao.addDirectorToMovieById(directorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDirectorAdded;
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
    public boolean updateDirectorPictureByDirectorId(long directorId, String picture) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateDirectorPictureByDirectorId(directorId, picture);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateDirectorInfoByDirectorId(long directorId, String firstName, String lastName,
                                                  Date birthDate, double height) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = movieDao.updateDirectorInfoByDirectorId(directorId, firstName, lastName, birthDate, height);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
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
    public List<Movie> findMoviesForDirector(long directorId) throws ServiceException {
        List<Movie> moviesForDirector;
        try {
            moviesForDirector = movieDao.findMoviesForDirector(directorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return moviesForDirector;
    }

    @Override
    public List<Director> findDirectorsByKeyWords(String keyWords) throws ServiceException {
        List<Director> directorsByKeyWords;
        try {
            directorsByKeyWords = movieDao.findDirectorsByKeyWords(keyWords);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return directorsByKeyWords;
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

    @Override
    public List<Director> findAllDirectors() throws ServiceException {
        List<Director> allDirectors;
        try {
            allDirectors = movieDao.findAllDirectors();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allDirectors;
    }
}
