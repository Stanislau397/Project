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
            statement.setString(5, movie.getCountry());
            statement.setString(6, movie.getDescription());
            statement.setString(7, movie.getPicture());
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
    public boolean updateTitleRunTimeReleaseDateDescriptionByMovieId(String title, int runTime, Date releaseDate, String description, long movie_id) throws DaoException {
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
        boolean isTrailerUpdated = false;
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
    public List<Movie> findAll() throws DaoException {
        List<Movie> allMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
            statement.setInt(2, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
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
    public List<Genre> findMovieGenresByMovieId(long movieId) throws DaoException {
        List<Genre> movieGenres = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_MOVIE_GENRES)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setGenreTitle(resultSet.getString(1));
                genre.setGenreId(resultSet.getLong(2));
                movieGenres.add(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return movieGenres;
    }

    @Override
    public List<Movie> findNewestMovies() throws DaoException {
        List<Movie> newestMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_NEW_MOVIES);
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
    public List<Movie> findCurrentYearMoviesByGenreTitle(String genreTitle) throws DaoException {
        List<Movie> currentYearMoviesByGenre = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_CURRENT_YEAR_MOVIES_BY_GENRE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.MOVIE_SCORE));
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
                Rating rating = new Rating();
                Genre genre = new Genre();
                genre.setGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTrailer(resultSet.getString(TableColumn.MOVIE_TRAILER));
                movie.setReleaseDate(resultSet.getDate(TableColumn.MOVIE_RELEASE_DATE));
                movie.setRunTime(resultSet.getInt(TableColumn.MOVIE_RUN_TIME));
                movie.setCountry(resultSet.getString(TableColumn.MOVIE_COUNTRY));
                movie.setDescription(resultSet.getString(TableColumn.MOVIE_DESCRIPTION));
                movie.setPicture(resultSet.getString(TableColumn.MOVIE_PICTURE));
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
    public List<Movie> findRatedMoviesByUserName(String userName) throws DaoException {
        List<Movie> ratedMovies = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_RATED_MOVIES)) {
            statement.setString(1, userName);
            statement.setString(2, userName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movie movie = new Movie();
                Rating rating = new Rating();
                Comment comment = new Comment();
                rating.setScore(resultSet.getInt(TableColumn.MOVIE_SCORE));
                comment.setText(resultSet.getString(TableColumn.COMMENT));
                comment.setPostDate(resultSet.getString(TableColumn.COMMENT_POST_DATE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Genre genre = new Genre();
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                genre.setGenreTitle(resultSet.getString(TableColumn.GENRE_TITLE));
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
                Comment comment = new Comment();
                Rating rating = new Rating();
                comment.setText(resultSet.getString(TableColumn.COMMENT));
                comment.setUserName(resultSet.getString(TableColumn.USER_NAME));
                rating.setScore(resultSet.getInt(TableColumn.MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.AVERAGE_MOVIE_SCORE));
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
    public boolean addActor(Actor actor) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_ACTOR)) {
            statement.setLong(1, actor.getActorId());
            statement.setString(2, actor.getFirstName());
            statement.setString(3, actor.getLastName());
            statement.setString(4, actor.getPicture());
            statement.setString(5, actor.getBirthDate());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean addActorToMovieById(long actorId, long movieId) throws DaoException {
        boolean isActorAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_ACTOR_TO_MOVIE_BY_ID)) {
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
    public boolean addActorToMovieByMovieId(Actor actor, long movieId) throws DaoException {
        boolean isActorAddedToMovie;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_ACTOR_TO_MOVIE)) {
            statement.setLong(1, actor.getActorId());
            statement.setLong(2, movieId);
            int update = statement.executeUpdate();
            isActorAddedToMovie = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isActorAddedToMovie;
    }

    @Override
    public boolean updateActorPictureByActorId(long actorId, String picture) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACTOR_PICTURE)) {
            statement.setString(1, picture);
            statement.setLong(2, actorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateActorInfoByActorId(long actorId, String firstName, String lastName, Date birth_date, double height) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACTOR)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setDate(3, birth_date);
            statement.setDouble(4, height);
            statement.setLong(5, actorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateActorFirstAndLastNameByActorId(String firstName, String lastName, long actorId) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_ACTOR)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setLong(3, actorId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean removeActorByActorId(long actorId) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_ACTOR)) {
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
    public boolean removeActorFromMovieById(long actorId, long movieId) throws DaoException {
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
    public boolean isActorAlreadyExists(String firstName, String lastName) throws DaoException {
        boolean isFound = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACTOR_BY_FIRST_LAST_NAME)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
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
    public Optional<Actor> findActorInfoByActorId(long actorId) throws DaoException {
        Optional<Actor> actorInfo = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ACTOR_INFO)) {
            statement.setLong(1, actorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Actor actor = new Actor();
                actor.setActorId(resultSet.getLong(TableColumn.ACTOR_ID));
                actor.setFirstName(resultSet.getString(TableColumn.ACTOR_FIRST_NAME));
                actor.setLastName(resultSet.getString(TableColumn.ACTOR_LAST_NAME));
                actor.setPicture(resultSet.getString(TableColumn.ACTOR_PICTURE));
                actor.setBirthDate(String.valueOf(resultSet.getDate(TableColumn.BIRTH_DATE)));
                actor.setAge(resultSet.getInt(TableColumn.AGE));
                actor.setHeight(resultSet.getDouble(TableColumn.HEIGHT));
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
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACTORS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long actorId = resultSet.getLong(TableColumn.ACTOR_ID);
                String firstName = resultSet.getString(TableColumn.ACTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.ACTOR_LAST_NAME);
                Actor actor = new Actor(actorId, firstName, lastName);
                actors.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actors;
    }

    @Override
    public Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws DaoException {
        Optional<Actor> actorOptional = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACTOR_BY_FIRST_LAST_NAME)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Actor actor = new Actor();
                actor.setActorId(resultSet.getLong(TableColumn.ACTOR_ID));
                actor.setFirstName(resultSet.getString(TableColumn.ACTOR_FIRST_NAME));
                actor.setLastName(resultSet.getString(TableColumn.ACTOR_LAST_NAME));
                actorOptional = Optional.of(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actorOptional;
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
    public List<Actor> findAllActors() throws DaoException {
        List<Actor> allActors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_ACTORS);
            while (resultSet.next()) {
                long actorId = resultSet.getLong(TableColumn.ACTOR_ID);
                String firstName = resultSet.getString(TableColumn.ACTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.ACTOR_LAST_NAME);
                Actor actor = new Actor(actorId, firstName, lastName);
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
                String firstName = resultSet.getString(TableColumn.ACTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.ACTOR_LAST_NAME);
                long actorId = resultSet.getLong(TableColumn.ACTOR_ID);
                Actor actor = new Actor(actorId, firstName, lastName);
                actorsByKeyWords.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actorsByKeyWords;
    }

    @Override
    public List<Director> findAllDirectors() throws DaoException {
        List<Director> allDirectors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_DIRECTORS);
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
                String firstName = resultSet.getString(TableColumn.ACTOR_FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.ACTOR_LAST_NAME);
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
                Rating rating = new Rating();
                rating.setScore(resultSet.getInt(TableColumn.MOVIE_SCORE));
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

    public boolean addGenre(Genre genre) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_GENRE)) {
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
    public boolean removeGenreById(long genreId) throws DaoException {
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
    public boolean addGenreToMovie(long genreId, long movieId) throws DaoException {
        boolean isGenreAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_MOVIE_GENRES)) {
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
    public Optional<Genre> findGenreByTitle(String genreTitle) throws DaoException {
        Optional<Genre> isFound = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_GENRE_BY_TITLE)) {
            statement.setString(1, genreTitle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long genreId = resultSet.getLong(TableColumn.GENRE_ID);
                String title = resultSet.getString(TableColumn.GENRE_TITLE);
                Genre genre = new Genre(genreId, title);
                isFound = Optional.of(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public boolean removeGenreFromMovieByMovieAndGenreId(long movieId, long genreId) throws DaoException {
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
            ResultSet resultSet = statement.executeQuery(SqlQuery.FIND_ALL_GENRES);
            while (resultSet.next()) {
                long genreId = resultSet.getLong(TableColumn.GENRE_ID);
                String genreTitle = resultSet.getString(TableColumn.GENRE_TITLE);
                Genre genre = new Genre(genreId, genreTitle);
                genres.add(genre);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return genres;
    }
}
