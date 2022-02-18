package edu.epam.project.service.impl;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.*;
import edu.epam.project.exception.AlreadyExistsException;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.validator.ActorValidator;
import edu.epam.project.validator.MovieValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {

    public static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private MovieDao movieDao = new MovieDaoImpl();


    @Override
    public boolean add(Movie movie) throws ServiceException, InvalidInputException {
        MovieValidator validator = new MovieValidator();
        String imagePath = movie.getPicture();
        String movieTitle = movie.getTitle();
        String movieDescription = movie.getDescription();
        String releaseDate = String.valueOf(movie.getReleaseDate());
        int runTime = movie.getRunTime();
        boolean isAdded = false;
        try {
            if (!movieTitle.isEmpty() && !releaseDate.isEmpty()) {
                validator.isImageValid(imagePath);
                validator.isTitleValid(movieTitle);
                validator.isDescriptionValid(movieDescription);
                validator.isReleaseDateValid(releaseDate);
                validator.isRunTimeValid(runTime);
                isAdded = movieDao.add(movie);
            }
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
    public boolean updateMovieInfoById(String title, int runTime, Date releaseDate, String description, long movie_id)
            throws ServiceException, InvalidInputException {
        MovieValidator validator = new MovieValidator();
        boolean isUpdated = false;
        try {
            if (validator.isTitleValid(title)
                    && validator.isDescriptionValid(description)
                    && validator.isRunTimeValid(runTime)
                    && validator.isReleaseDateValid(String.valueOf(releaseDate))) {
                isUpdated = movieDao.updateMovieInfoById(title, runTime, releaseDate, description, movie_id);
            }
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
    public boolean movieExistsById(long movieId) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.movieExistsById(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
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
    public int countNewestMovies() throws ServiceException {
        int amountOfNewestMovies;
        try {
            amountOfNewestMovies = movieDao.countNewestMovies();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfNewestMovies;
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
    public boolean addGenre(Genre genre) throws ServiceException, AlreadyExistsException {
        boolean isAdded;
        String genreTitle = genre.getGenreTitle();
        try {
            boolean genreExists = genreExistsByGenreTitle(genreTitle);
            if (genreExists) {
                throw new AlreadyExistsException("This genre already exists");
            }
            isAdded = movieDao.addGenre(genre);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteGenreById(long genreId) throws ServiceException {
        boolean isGenreDeleted = false;
        try {
            boolean genreExists = genreExistsByGenreId(genreId);
            if (genreExists) {
                isGenreDeleted = movieDao.deleteGenreById(genreId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreDeleted;
    }

    @Override
    public boolean genreExistsByGenreId(long genreId) throws ServiceException {
        boolean isGenreExists;
        try {
            isGenreExists = movieDao.genreExistsByGenreId(genreId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreExists;
    }

    @Override
    public boolean genreExistsByGenreTitle(String genreTitle) throws ServiceException {
        boolean isGenreExists;
        try {
            isGenreExists = movieDao.genreExistsByGenreTitle(genreTitle);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreExists;
    }

    @Override
    public boolean genreExistsInMovieByMovieIdAndGenreId(long movieId, long genreId) throws ServiceException {
        boolean isFound;
        try {
            isFound = movieDao.genreExistsInMovieByMovieIdAndGenreId(movieId, genreId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public boolean addGenreToMovieByGenreIdAndMovieId(long genreId, long movieId) throws ServiceException {
        boolean isGenreAdded = false;
        try {
            boolean genreExists = genreExistsByGenreId(genreId);
            boolean movieExists = movieExistsById(movieId);
            boolean genreExistsInMovie = genreExistsInMovieByMovieIdAndGenreId(movieId, genreId);
            if (genreExists && movieExists && !genreExistsInMovie) {
                isGenreAdded = movieDao.addGenreToMovieByGenreIdAndMovieId(genreId, movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreAdded;
    }

    @Override
    public boolean deleteGenreFromMovieByGenreIdAndMovieId(long genreId, long movieId) throws ServiceException {
        boolean isGenreDeleted = false;
        try {
            boolean genreExists = genreExistsByGenreId(genreId);
            boolean movieExists = movieExistsById(movieId);
            boolean genreExistsInMovie = genreExistsInMovieByMovieIdAndGenreId(movieId, genreId);
            if (genreExists && movieExists && genreExistsInMovie) {
                isGenreDeleted = movieDao.deleteGenreFromMovieByMovieIdAndGenreId(movieId, genreId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isGenreDeleted;
    }

    @Override
    public List<Genre> findGenresForMovieByMovieId(long movieId) throws ServiceException {
        List<Genre> movieGenres = new ArrayList<>();
        try {
            boolean movieExists = movieExistsById(movieId);
            if (movieExists) {
                movieGenres = movieDao.findGenresForMovieByMovieId(movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movieGenres;
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
    public boolean addCountry(Country country) throws ServiceException {
        boolean isCountryAdded = false;
        String countryName = country.getCountryName();
        try {
            boolean countryExists = countryExistsByName(countryName);
            if (!countryExists) {
                isCountryAdded = movieDao.addCountry(country);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isCountryAdded;
    }

    @Override
    public boolean addCountryToMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException {
        boolean isCountryAddedToMovie = false;
        try {
            boolean movieExists = movieExistsById(movieId);
            boolean countryExists = countryExistsById(countryId);
            boolean countryExistsInMovie = countryExistsInMovieByMovieIdAndCountryId(movieId, countryId);
            if (movieExists && countryExists && !countryExistsInMovie) {
                isCountryAddedToMovie = movieDao.addCountryToMovieByMovieIdAndCountryId(movieId, countryId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isCountryAddedToMovie;
    }

    @Override
    public boolean removeCountryById(long countryId) throws ServiceException {
        boolean isCountryRemoved = false;
        try {
            boolean countryExists = countryExistsById(countryId);
            if (countryExists) {
                isCountryRemoved = movieDao.deleteCountryById(countryId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isCountryRemoved;
    }

    @Override
    public boolean removeCountryFromMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException {
        boolean isCountryRemovedFromMovie = false;
        try {
            boolean movieExists = movieExistsById(movieId);
            boolean countryExists = countryExistsById(countryId);
            boolean countryExistsInMovie = countryExistsInMovieByMovieIdAndCountryId(movieId, countryId);
            if (movieExists && countryExists && countryExistsInMovie) {
                isCountryRemovedFromMovie = movieDao.deleteCountryFromMovieByMovieIdAndCountryId(movieId, countryId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isCountryRemovedFromMovie;
    }

    @Override
    public boolean countryExistsByName(String countryName) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.countryExistsByCountryName(countryName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean countryExistsById(long countryId) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.countryExistsByCountryId(countryId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean countryExistsInMovieByMovieIdAndCountryId(long movieId, long countryId) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.countryExistsInMovieByMovieIdAndCountryId(movieId, countryId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public List<Country> findAllCountries() throws ServiceException {
        List<Country> allCountries;
        try {
            allCountries = movieDao.findAllCountries();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allCountries;
    }

    @Override
    public List<Country> findCountriesForMovieByMovieId(long movieId) throws ServiceException {
        List<Country> movieCountries = new ArrayList<>();
        try {
            if (movieExistsById(movieId)) {
                movieCountries = movieDao.findCountriesForMovieByMovieId(movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movieCountries;
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
    public List<Movie> findNewestMovies(int page, int total) throws ServiceException {
        List<Movie> newestMovies;
        try {
            newestMovies = movieDao.findNewestMovies(page, total);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return newestMovies;
    }

    @Override
    public List<Movie> findMoviesWithTrailer() throws ServiceException {
        List<Movie> trailers;
        try {
            trailers = movieDao.findMoviesWithTrailer();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return trailers;
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
    public Movie findMovieById(long movieId) throws ServiceException {
        Optional<Movie> isFound;
        CommentService commentService = new CommentServiceImpl();
        Movie movie = new Movie();
        try {
            isFound = movieDao.findMovieById(movieId);
            List<Genre> genres = findGenresForMovieByMovieId(movieId);
            List<Comment> comments = commentService.findCommentsByMovieId(movieId);
            List<Actor> actors = findActorsByMovieId(movieId);
            List<Director> directors = findDirectorsByMovieId(movieId);
            List<Country> countries = findCountriesForMovieByMovieId(movieId);
            if (isFound.isPresent()) {
                movie = isFound.get();
                movie.setGenres(genres);
                movie.setComments(comments);
                movie.setActors(actors);
                movie.setDirectors(directors);
                movie.setCountries(countries);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return movie;
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
    public boolean addActor(Actor actor) throws ServiceException, InvalidInputException {
        boolean isAdded = false;
        ActorValidator validator = new ActorValidator();
        String firstName = actor.getFirstName();
        String lastName = actor.getLastName();
        try {
            boolean actorExists = actorExistsByFirstnameAndLastname(firstName, lastName);
            boolean actorValid = validator.isValidActor(firstName, lastName);
            if (!actorExists && actorValid) {
                isAdded = movieDao.addActor(actor);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addActorToMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException {
        boolean isActorAddedToMovie = false;
        try {
            boolean actorExists = actorExistsById(actorId);
            boolean movieExists = movieExistsById(movieId);
            if (actorExists && movieExists) {
                isActorAddedToMovie = movieDao.addActorToMovieByActorIdAndMovieId(actorId, movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorAddedToMovie;
    }

    @Override
    public boolean updateActorByActorId(long actorId, Actor actor) throws ServiceException {
        boolean isActorUpdate = false;
        ActorValidator actorValidator = new ActorValidator();
        String firstname = actor.getFirstName();
        String lastname = actor.getLastName();
        try {
            boolean actorValid = actorValidator.isValidActor(firstname, lastname);
            boolean actorExists = actorExistsById(actorId);
            if (actorExists && actorValid) {
                isActorUpdate = movieDao.updateActorById(actorId, actor);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorUpdate;
    }

    @Override
    public boolean deleteActorByActorId(long actorId) throws ServiceException {
        boolean isActorDeleted = false;
        try {
            boolean actorExists = actorExistsById(actorId);
            if (actorExists) {
                isActorDeleted = movieDao.deleteActorById(actorId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorDeleted;
    }

    @Override
    public boolean deleteActorFromMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException {
        boolean isActorDeletedFromMovie = false;
        try {
            boolean actorExists = actorExistsById(actorId);
            boolean movieExists = movieExistsById(movieId);
            if (actorExists && movieExists) {
                isActorDeletedFromMovie = movieDao.deleteActorFromMovieByActorIdAndMovieId(actorId, movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorDeletedFromMovie;
    }

    @Override
    public boolean actorExistsById(long actorId) throws ServiceException {
        boolean isActorExists;
        try {
            isActorExists = movieDao.actorExistsById(actorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isActorExists;
    }

    @Override
    public boolean actorExistsByFirstnameAndLastname(String firstName, String lastName) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.actorExistsByFirstnameAndLastname(firstName, lastName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean actorExistsInMovieByActorIdAndMovieId(long actorId, long movieId) throws ServiceException {
        boolean exists;
        try {
            exists = movieDao.actorExistsInMovieByActorIdAndMovieId(actorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public Actor findActorById(long actorId) throws ServiceException {
        Optional<Actor> actorInfo;
        Actor actor = Actor.newActorBuilder().build();
        try {
            actorInfo = movieDao.findActorById(actorId);
            if (actorInfo.isPresent()) {
                actor = actorInfo.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actor;
    }

    @Override
    public List<Actor> findActorsByMovieId(long movieId) throws ServiceException {
        List<Actor> actors = new ArrayList<>();
        try {
            boolean movieExists = movieExistsById(movieId);
            if (movieExists) {
                actors = movieDao.findActorsByMovieId(movieId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actors;
    }

    @Override
    public List<Actor> findAllActors(int start, int total) throws ServiceException {
        List<Actor> allActors;
        try {
            allActors = movieDao.findAllActors(start, total);
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
    public boolean removeDirectorById(long directorId) throws ServiceException {
        boolean isDeleted;
        try {
            isDeleted = movieDao.removeDirectorById(directorId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDeleted;
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
    public boolean isDirectorAlreadyExistsInMovie(long directorId, long movieId) throws ServiceException {
        boolean isDirectorFound;
        try {
            isDirectorFound = movieDao.isDirectorAlreadyExistsInMovie(directorId, movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isDirectorFound;
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
    public List<Director> findAllDirectors(int start, int total) throws ServiceException {
        List<Director> allDirectors;
        try {
            allDirectors = movieDao.findAllDirectors(start, total);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return allDirectors;
    }
}
