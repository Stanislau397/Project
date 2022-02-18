package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.*;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class MovieDaoImpl implements MovieDao {

    public static final Logger logger = LogManager.getLogger(MovieDaoImpl.class);

    @Override
    public boolean add(Movie movie) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_MOVIE)) {
            statement.setDouble(1, movie.getMovieId());
            statement.setString(2, movie.getTitle());
            statement.setDate(3, movie.getReleaseDate());
            statement.setInt(4, movie.getRunTime());
            statement.setString(5, movie.getDescription());
            statement.setString(6, movie.getPicture());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateMoviePosterByMovieId(String picturePath, long movieId) throws DaoException {
        boolean isPosterUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_MOVIE_POSTER)) {
            statement.setString(1, picturePath);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isPosterUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isPosterUpdated;
    }

    @Override
    public boolean updateMovieInfoById(String title, int runTime, Date releaseDate, String description, long movie_id) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_TITLE_RUNTIME_DESCRIPTION_DATE)) {
            statement.setString(1, title);
            statement.setInt(2, runTime);
            statement.setDate(3, releaseDate);
            statement.setString(4, description);
            statement.setLong(5, movie_id);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateMovieTrailerByMovieId(long movieId, String trailer) throws DaoException {
        boolean isTrailerUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_MOVIE_TRAILER)) {
            statement.setString(1, trailer);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isTrailerUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isTrailerUpdated;
    }

    @Override
    public boolean movieExistsById(long movieId) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_BY_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public int countMovies() throws DaoException {
        int counter = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_MOVIES);
            if (resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return counter;
    }

    @Override
    public int countNewestMovies() throws DaoException {
        int amountOfNewestMovies = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_NEWEST_MOVIES);
            if (resultSet.next()) {
                amountOfNewestMovies = resultSet.getInt(TableColumn.COUNTER);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return amountOfNewestMovies;
    }

    @Override
    public int countUserRatedMovies(String userName) throws DaoException {
        int counter = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COUNT_USER_RATED_MOVIES)) {
            statement.setString(1, userName);
            statement.setString(2, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                counter = resultSet.getInt(TableColumn.COUNTER);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return counter;
    }

    @Override
    public Optional<Movie> findMoviePosterByMovieId(long movieId) throws DaoException {
        Optional<Movie> moviePoster = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_POSTER)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                movie.setPicture(resultSet.getString(1));
                moviePoster = Optional.of(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviePoster;
    }

    @Override
    public List<Movie> findAll(int page, int total) throws DaoException {
        List<Movie> allMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_MOVIES)) {
            statement.setInt(1, page);
            statement.setInt(2, total);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                allMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allMovies;
    }

    @Override
    public List<Movie> findAllCurrentYearMovies() throws DaoException {
        List<Movie> currentYearMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_CURRENT_YEAR_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                currentYearMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return currentYearMovies;
    }

    @Override
    public List<Integer> findAllMovieYears() throws DaoException {
        List<Integer> years = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_MOVIES_YEARS);
            while (resultSet.next()) {
                int year = resultSet.getInt(1);
                years.add(year);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return years;
    }

    @Override
    public List<Movie> findMoviesByYear(int year) throws DaoException {
        List<Movie> moviesByYear = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_BY_YEAR)) {
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                moviesByYear.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesByYear;
    }

    @Override
    public List<Movie> findMoviesByGenre(Genre genre) throws DaoException {
        List<Movie> moviesByGenre = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIES_BY_GENRE)) {
            statement.setString(1, genre.getGenreTitle());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                moviesByGenre.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesByGenre;
    }

    @Override
    public List<Movie> findMoviesByGenreAndYear(Genre genre, Integer year) throws DaoException {
        List<Movie> moviesByGenreAndYear = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIES_BY_GENRE_AND_YEAR)) {
            statement.setString(1, genre.getGenreTitle());
            statement.setObject(2, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setGenre(genre);
                movie.setRating(rating);
                moviesByGenreAndYear.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesByGenreAndYear;
    }

    @Override
    public List<Genre> findGenresForMovieByMovieId(long movieId) throws DaoException {
        List<Genre> movieGenres = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_GENRES)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = Genre.newGenreBuilder()
                        .withGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE))
                        .withId(resultSet.getLong(TableColumn.GENRE_ID))
                        .build();
                movieGenres.add(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return movieGenres;
    }

    @Override
    public List<Movie> findNewestMovies(int page, int total) throws DaoException {
        List<Movie> newestMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_NEW_MOVIES)) {
            statement.setInt(1, page);
            statement.setInt(2, total);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                newestMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return newestMovies;
    }

    @Override
    public List<Movie> findUpcomingMovies() throws DaoException {
        List<Movie> upcomingMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_UPCOMING_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setTrailer(resultSet.getString(TableColumn.MOVIE_TRAILER));
                movie.setRating(rating);
                upcomingMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return upcomingMovies;
    }

    @Override
    public List<Movie> findUpcomingMoviesByGenreTitle(String genreTitle) throws DaoException {
        List<Movie> upcomingMoviesByGenre = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_UPCOMING_MOVIES_BY_GENRE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                upcomingMoviesByGenre.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return upcomingMoviesByGenre;
    }

    @Override
    public List<Movie> findMoviesWithTrailer() throws DaoException {
        List<Movie> moviesWithTrailer = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_MOVIES_WITH_TRAILER);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setRating(rating);
                movie.setTrailer(resultSet.getString(TableColumn.MOVIE_TRAILER));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                moviesWithTrailer.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesWithTrailer;
    }

    @Override
    public List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws DaoException {
        List<Movie> currentYearMoviesByGenre = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_CURRENT_YEAR_MOVIES_BY_GENRE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                currentYearMoviesByGenre.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return currentYearMoviesByGenre;
    }

    @Override
    public List<Movie> findNewestMoviesByGenreTitle(String genreTitle) throws DaoException {
        List<Movie> newestMoviesByGenre = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_NEW_MOVIES_BY_GENRE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                newestMoviesByGenre.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return newestMoviesByGenre;
    }

    @Override
    public List<Movie> findMostRatedMovies() throws DaoException {
        List<Movie> mostRatedMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_MOST_RATED_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                mostRatedMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return mostRatedMovies;
    }

    @Override
    public List<Movie> findMoviesForActorByActorId(long actorId) throws DaoException {
        List<Movie> moviesForActor = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIES_FOR_ACTOR)) {
            statement.setLong(1, actorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRating(rating);
                moviesForActor.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesForActor;
    }

    @Override
    public Optional<Movie> findLatestHighRatedMovieForUser(String userName) throws DaoException {
        Optional<Movie> latestHighScoredMovie = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_LATEST_HIGH_SCORE)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setRating(rating);
                latestHighScoredMovie = Optional.of(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestHighScoredMovie;
    }

    @Override
    public Optional<Movie> findLatestLowRatedMovieForUser(String userName) throws DaoException {
        Optional<Movie> latestLowScoredMovie = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_LATEST_LOW_SCORE)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setRating(rating);
                latestLowScoredMovie = Optional.of(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestLowScoredMovie;
    }

    @Override
    public Optional<Movie> findMovieByTitle(String title) throws DaoException {
        Optional<Movie> isFound;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_BY_TITLE)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Movie movie = new Movie();
            movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
            movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
            movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
            movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
            movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
            movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
            isFound = Optional.of(movie);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public Optional<Movie> findMovieById(long movieId) throws DaoException {
        Optional<Movie> isFound = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_BY_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                Genre genre = Genre.newGenreBuilder()
                        .withGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE))
                        .build();
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTrailer(resultSet.getString(TableColumn.MOVIE_TRAILER));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                movie.setGenre(genre);
                isFound = Optional.of(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public List<Movie> findRatedMoviesByUserName(String userName, int start, int total) throws DaoException {
        List<Movie> ratedMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_RATED_MOVIES)) {
            statement.setString(1, userName);
            statement.setString(2, userName);
            statement.setInt(3, start);
            statement.setInt(4, total);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                Comment comment = Comment.newCommentBuilder()
                        .withText(resultSet.getString(TableColumn.TEXT))
                        .withPostDate(resultSet.getTimestamp(TableColumn.COMMENT_POST_DATE))
                        .build();
                movie.setMovieId(resultSet.getInt(TableColumn.MOVIE_ID));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setRating(rating);
                movie.setComment(comment);
                ratedMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return ratedMovies;
    }

    @Override
    public List<Movie> findMoviesByKeyWord(String keyWord) throws DaoException {
        List<Movie> moviesByKeyWord = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_BY_KEY_WORD)) {
            statement.setString(1, keyWord);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setRating(rating);
                moviesByKeyWord.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesByKeyWord;
    }

    @Override
    public List<Movie> findLatestUploadedMovies() throws DaoException {
        List<Movie> latestMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LATEST_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Genre genre = Genre.newGenreBuilder()
                        .withGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setGenre(genre);
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                latestMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestMovies;
    }

    @Override
    public List<Movie> findLatestReviewedMovies() throws DaoException {
        List<Movie> latestReviewedMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LATEST_REVIEWED_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                User user = User.newUserBuilder()
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .build();
                Comment comment = Comment.newCommentBuilder()
                        .withText(resultSet.getString(TableColumn.TEXT))
                        .withUser(user)
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setComment(comment);
                movie.setRating(rating);
                latestReviewedMovies.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestReviewedMovies;
    }

    @Override
    public List<Movie> findBestMoviesForActorByActorId(long actorId) throws DaoException {
        List<Movie> bestMoviesForActor = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_BEST_MOVIES_FOR_ACTOR)) {
            statement.setLong(1, actorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                bestMoviesForActor.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return bestMoviesForActor;
    }

    @Override
    public List<Movie> findBestMoviesForDirectorByDirectorId(long directorId) throws DaoException {
        List<Movie> bestMoviesForDirector = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_BEST_MOVIES_FOR_DIRECTOR)) {
            statement.setLong(1, directorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE))
                        .build();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setRating(rating);
                bestMoviesForDirector.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return bestMoviesForDirector;
    }

    @Override
    public Optional<Director> findDirectorInfoByDirectorId(long directorId) throws DaoException {
        Optional<Director> directorInfo = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_DIRECTOR_INFO)) {
            statement.setLong(1, directorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Director director = new Director();
                director.setDirectorId(resultSet.getLong(TableColumn.DIRECTOR_ID));
                director.setFirstName(resultSet.getString(TableColumn.DIRECTOR_FIRST_NAME));
                director.setLastName(resultSet.getString(TableColumn.DIRECTOR_LAST_NAME));
                director.setPicture(resultSet.getString(TableColumn.ACTOR_PICTURE));
                director.setBirthDate(String.valueOf(resultSet.getDate(TableColumn.BIRTH_DATE)));
                director.setAge(resultSet.getInt(TableColumn.AGE));
                director.setHeight(resultSet.getDouble(TableColumn.HEIGHT));
                directorInfo = Optional.of(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directorInfo;
    }

    @Override
    public List<Director> findAllDirectors(int start, int total) throws DaoException {
        List<Director> allDirectors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_DIRECTORS)) {
            statement.setInt(1, start);
            statement.setInt(2, total);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long directorId = resultSet.getLong(TableColumn.DIRECTOR_ID);
                String firstName = resultSet.getString(TableColumn.DIRECTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.DIRECTOR_LAST_NAME);
                Director director = new Director(directorId, firstName, lastName);
                allDirectors.add(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allDirectors;
    }

    @Override
    public List<Director> findDirectorsByKeyWords(String keyWords) throws DaoException {
        List<Director> directorsByKeyWords = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_DIRECTORS_BY_KEY_WORDS)) {
            statement.setString(1, keyWords);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString(TableColumn.DIRECTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.DIRECTOR_LAST_NAME);
                long directorId = resultSet.getLong(TableColumn.DIRECTOR_ID);
                Director director = new Director(directorId, firstName, lastName);
                directorsByKeyWords.add(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directorsByKeyWords;
    }

    @Override
    public boolean addDirector(Director director) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_DIRECTOR)) {
            statement.setLong(1, director.getDirectorId());
            statement.setString(2, director.getFirstName());
            statement.setString(3, director.getLastName());
            statement.setString(4, director.getPicture());
            statement.setString(5, director.getBirthDate());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addDirectorToMovieById(long directorId, long movieId) throws DaoException {
        boolean isDirectorAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_DIRECTOR_TO_MOVIE)) {
            statement.setLong(1, directorId);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isDirectorAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDirectorAdded;
    }

    @Override
    public boolean updateDirectorPictureByDirectorId(long directorId, String picture) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_DIRECTOR_PICTURE)) {
            statement.setString(1, picture);
            statement.setLong(2, directorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateDirectorInfoByDirectorId(long directorId, String firstName, String lastName, Date birthDate, double height) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_DIRECTOR_INFO)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDouble(3, height);
            statement.setDate(4, birthDate);
            statement.setLong(5, directorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean removeDirectorById(long directorId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_DIRECTOR)) {
            statement.setLong(1, directorId);
            int update = statement.executeUpdate();
            isDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean removeDirectorFromMovie(long directorId, long movieId) throws DaoException {
        boolean isDirectorRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_DIRECTOR_FROM_MOVIE)) {
            statement.setLong(1, directorId);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isDirectorRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDirectorRemoved;
    }

    @Override
    public boolean isDirectorAlreadyExists(Director director) throws DaoException {
        boolean isDirectorExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_DIRECTOR)) {
            statement.setString(1, director.getFirstName());
            statement.setString(2, director.getLastName());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isDirectorExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDirectorExists;
    }

    @Override
    public boolean isDirectorAlreadyExistsInMovie(long directorId, long movieId) throws DaoException {
        boolean isDirectorFound = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_DIRECTOR_FOR_MOVIE)) {
            statement.setLong(1, directorId);
            statement.setLong(2, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isDirectorFound = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDirectorFound;
    }

    @Override
    public boolean addDirectorToMovieByMovieId(Director director, long movieId) throws DaoException {
        boolean isDirectorAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_MOVIE_DIRECTION)) {
            statement.setLong(1, movieId);
            statement.setLong(2, director.getDirectorId());
            int update = statement.executeUpdate();
            isDirectorAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDirectorAdded;
    }

    @Override
    public Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws DaoException {
        Optional<Director> directorOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_DIRECTOR)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long directorId = resultSet.getLong(TableColumn.DIRECTOR_ID);
                String directorFirstName = resultSet.getString(TableColumn.DIRECTOR_FIRST_NAME);
                String directorLastName = resultSet.getString(TableColumn.DIRECTOR_LAST_NAME);
                Director director = new Director(directorId, directorFirstName, directorLastName);
                directorOptional = Optional.of(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directorOptional;
    }

    @Override
    public List<Director> findDirectorsByMovieId(long movieId) throws DaoException {
        List<Director> directors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_DIRECTORS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long directorId = resultSet.getLong(TableColumn.DIRECTOR_ID);
                String firstName = resultSet.getString(TableColumn.FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.LAST_NAME);
                Director director = new Director(directorId, firstName, lastName);
                directors.add(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directors;
    }

    @Override
    public List<Movie> findMoviesForDirector(long directorId) throws DaoException {
        List<Movie> moviesForDirector = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIES_FOR_DIRECTOR)) {
            statement.setLong(1, directorId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = Rating.newRatingBuilder()
                        .withScore(resultSet.getInt(TableColumn.MOVIE_SCORE))
                        .build();
                movie.setRating(rating);
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                moviesForDirector.add(movie);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return moviesForDirector;
    }

    @Override
    public boolean addActor(Actor actor) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_ACTOR)) {
            statement.setLong(1, actor.getActorId());
            statement.setString(2, actor.getFirstName());
            statement.setString(3, actor.getLastName());
            statement.setString(4, actor.getPicture());
            statement.setDate(5, Date.valueOf(actor.getBirthDate()));
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addActorToMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException {
        boolean isActorAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_ACTOR_TO_MOVIE)) {
            statement.setLong(1, actorId);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isActorAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isActorAdded;
    }

    @Override
    public boolean updateActorById(long actorId, Actor actor) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACTOR_BY_ID)) {
            statement.setString(1, actor.getFirstName());
            statement.setString(2, actor.getLastName());
            statement.setDate(3, Date.valueOf(actor.getBirthDate()));
            statement.setDouble(4, actor.getHeight());
            statement.setString(5, actor.getPicture());
            statement.setLong(6, actorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteActorById(long actorId) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_ACTOR_BY_ID)) {
            statement.setLong(1, actorId);
            int update = statement.executeUpdate();
            isRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRemoved;
    }

    @Override
    public boolean deleteActorFromMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException {
        boolean isActorDeleted = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_ACTOR_FROM_MOVIE)) {
            statement.setLong(1, actorId);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isActorDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
        }
        return isActorDeleted;
    }

    @Override
    public boolean actorExistsById(long actorId) throws DaoException {
        boolean actorExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTOR_BY_ID)) {
            statement.setLong(1, actorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                actorExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actorExists;
    }

    @Override
    public boolean actorExistsByFirstnameAndLastname(String firstName, String lastName) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTOR_BY_FIRSTNAME_AND_LASTNAME)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public boolean actorExistsInMovieByActorIdAndMovieId(long actorId, long movieId) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTOR_FOR_MOVIE)) {
            statement.setLong(1, actorId);
            statement.setLong(2, movieId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public Optional<Actor> findActorById(long actorId) throws DaoException {
        Optional<Actor> actorInfo = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTOR_BY_ID)) {
            statement.setLong(1, actorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Date date = resultSet.getDate(TableColumn.BIRTH_DATE);
                LocalDate birthDate = null;
                if (date != null) {
                    birthDate = date.toLocalDate();
                }
                Actor actor = Actor.newActorBuilder()
                        .withId(resultSet.getLong(TableColumn.ACTOR_ID))
                        .withFirstname(resultSet.getString(TableColumn.FIRST_NAME))
                        .withLastname(resultSet.getString(TableColumn.LAST_NAME))
                        .withPicture(resultSet.getString(TableColumn.ACTOR_PICTURE))
                        .withBirthDate(birthDate)
                        .withAge(resultSet.getInt(TableColumn.AGE))
                        .withHeight(resultSet.getDouble(TableColumn.HEIGHT))
                        .build();
                actorInfo = Optional.of(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actorInfo;
    }

    @Override
    public List<Actor> findActorsByMovieId(long movieId) throws DaoException {
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTORS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Actor actor = Actor.newActorBuilder()
                        .withId(resultSet.getLong(TableColumn.ACTOR_ID))
                        .withFirstname(resultSet.getString(TableColumn.FIRST_NAME))
                        .withLastname(resultSet.getString(TableColumn.LAST_NAME))
                        .build();
                actors.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actors;
    }

    @Override
    public List<Actor> findAllActors(int start, int total) throws DaoException {
        List<Actor> allActors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_ACTORS)) {
            statement.setInt(1, start);
            statement.setInt(2, total);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Actor actor = Actor.newActorBuilder()
                        .withId(resultSet.getLong(TableColumn.ACTOR_ID))
                        .withFirstname(resultSet.getString(TableColumn.FIRST_NAME))
                        .withLastname(resultSet.getString(TableColumn.LAST_NAME))
                        .build();
                allActors.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allActors;
    }

    @Override
    public List<Actor> findActorsByKeyWords(String keyWords) throws DaoException {
        List<Actor> actorsByKeyWords = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTORS_BY_KEY_WORDS)) {
            statement.setString(1, keyWords);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Actor actor = Actor.newActorBuilder()
                        .withId(resultSet.getLong(TableColumn.ACTOR_ID))
                        .withFirstname(resultSet.getString(TableColumn.FIRST_NAME))
                        .withLastname(resultSet.getString(TableColumn.LAST_NAME))
                        .build();
                actorsByKeyWords.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actorsByKeyWords;
    }

    @Override
    public int countActors() throws DaoException {
        int actors = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_ALL_ACTORS);
            if (resultSet.next()) {
                actors = resultSet.getInt(TableColumn.COUNTER);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actors;
    }

    public boolean addGenre(Genre genre) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_INTO_GENRE)) {
            statement.setLong(1, genre.getGenreId());
            statement.setString(2, genre.getGenreTitle());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean deleteGenreById(long genreId) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_GENRE_BY_ID)) {
            statement.setLong(1, genreId);
            int update = statement.executeUpdate();
            isDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDeleted;
    }

    @Override
    public boolean addGenreToMovieByGenreIdAndMovieId(long genreId, long movieId) throws DaoException {
        boolean isGenreAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_INTO_MOVIE_GENRES)) {
            statement.setLong(1, movieId);
            statement.setLong(2, genreId);
            int update = statement.executeUpdate();
            isGenreAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isGenreAdded;
    }

    @Override
    public boolean genreExistsByGenreTitle(String genreTitle) throws DaoException {
        boolean isGenreExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_GENRE_BY_TITLE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isGenreExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isGenreExists;
    }

    @Override
    public boolean genreExistsByGenreId(long genreId) throws DaoException {
        boolean isGenreExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_GENRE_BY_ID)) {
            statement.setLong(1, genreId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isGenreExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isGenreExists;
    }

    @Override
    public boolean genreExistsInMovieByMovieIdAndGenreId(long movieId, long genreId) throws DaoException {
        boolean isFound = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_MOVIE_GENRE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, genreId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isFound = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public boolean deleteGenreFromMovieByMovieIdAndGenreId(long movieId, long genreId) throws DaoException {
        boolean isGenreRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_GENRE_FROM_MOVIE)) {
            statement.setLong(1, genreId);
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isGenreRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isGenreRemoved;
    }

    @Override
    public List<Genre> findAllGenres() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_GENRES);
            while (resultSet.next()) {
                Genre genre = Genre.newGenreBuilder()
                        .withId(resultSet.getLong(TableColumn.GENRE_ID))
                        .withGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE))
                        .build();
                genres.add(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return genres;
    }

    @Override
    public int countGenres() throws DaoException {
        int genres = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_ALL_GENRES);
            if (resultSet.next()) {
                genres = resultSet.getInt(TableColumn.COUNTER);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return genres;
    }

    @Override
    public int countDirectors() throws DaoException {
        int directors = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_ALL_DIRECTORS);
            if (resultSet.next()) {
                directors = resultSet.getInt(TableColumn.COUNTER);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directors;
    }

    @Override
    public boolean addCountry(Country country) throws DaoException {
        boolean isCountryAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_COUNTRY)) {
            statement.setString(1, country.getCountryName());
            int update = statement.executeUpdate();
            isCountryAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isCountryAdded;
    }

    @Override
    public boolean deleteCountryById(long countryId) throws DaoException {
        boolean isCountryRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_COUNTRY_BY_ID)) {
            statement.setLong(1, countryId);
            int update = statement.executeUpdate();
            isCountryRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isCountryRemoved;
    }

    @Override
    public boolean addCountryToMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException {
        boolean countryAddedToMovie;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_COUNTRY_TO_MOVIE_COUNTRIES)) {
            statement.setLong(1, movieId);
            statement.setLong(2, countryId);
            int update = statement.executeUpdate();
            countryAddedToMovie = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countryAddedToMovie;
    }

    @Override
    public boolean countryExistsByCountryName(String countryName) throws DaoException {
        boolean countryExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COUNTRY_BY_NAME)) {
            statement.setString(1, countryName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countryExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countryExists;
    }

    @Override
    public boolean countryExistsByCountryId(long countryId) throws DaoException {
        boolean countryExists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COUNTRY_BY_ID)) {
            statement.setLong(1, countryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                countryExists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return countryExists;
    }

    @Override
    public boolean countryExistsInMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_COUNTRY_FOR_MOVIE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, countryId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public List<Country> findAllCountries() throws DaoException {
        List<Country> allCountries = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_COUNTRIES);
            while (resultSet.next()) {
                Country country = Country.newCountryBuilder()
                        .withId(resultSet.getInt(TableColumn.COUNTRY_ID))
                        .withCountryName(resultSet.getString(TableColumn.COUNTRY_NAME))
                        .build();
                allCountries.add(country);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allCountries;
    }

    @Override
    public List<Country> findCountriesForMovieByMovieId(long movieId) throws DaoException {
        List<Country> movieCountries = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_COUNTRIES)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Country country = Country.newCountryBuilder()
                        .withId(resultSet.getInt(TableColumn.COUNTRY_ID))
                        .withCountryName(resultSet.getString(TableColumn.COUNTRY_NAME))
                        .build();
                movieCountries.add(country);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return movieCountries;
    }

    @Override
    public boolean deleteCountryFromMovieByMovieIdAndCountryId(long movieId, long countryId) throws DaoException {
        boolean isCountryRemovedFromMovie;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_COUNTRY_FROM_MOVIE)) {
            statement.setLong(1, movieId);
            statement.setLong(2, countryId);
            int update = statement.executeUpdate();
            isCountryRemovedFromMovie = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isCountryRemovedFromMovie;
    }
}
