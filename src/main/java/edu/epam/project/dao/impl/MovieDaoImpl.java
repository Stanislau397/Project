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
    public boolean deleteMovieByTitle(String title) throws DaoException {
        boolean isDeleted;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_BY_TITLE)) {
            statement.setString(1, title);
            int update = statement.executeUpdate();
            isDeleted = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isDeleted;
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
            statement.setString(1, genre.getTitle());
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
                genre.setTitle(resultSet.getString(TableColumn.GENRE_TITLE));
                movie.setTitle(resultSet.getString(TableColumn.MOVIE_TITLE));
                movie.setMovieId(resultSet.getLong(TableColumn.MOVIE_ID));
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
    public boolean addActor(Actor actor) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_ACTOR)) {
            statement.setLong(1, actor.getActorId());
            statement.setString(2, actor.getFirstName());
            statement.setString(3, actor.getLastName());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
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
    public boolean removeActorByFirstName(String firstName) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_ACTOR_BY_NAME)) {
            statement.setString(1, firstName);
            int update = statement.executeUpdate();
            isRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRemoved;
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
    public boolean addDirector(Director director) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_DIRECTOR)) {
            statement.setLong(1, director.getDirectorId());
            statement.setString(2, director.getFirstName());
            statement.setString(3, director.getLastName());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
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

    public boolean addGenre(Genre genre) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_GENRE)) {
            statement.setLong(1, genre.getGenreId());
            statement.setString(2, genre.getTitle());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
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
