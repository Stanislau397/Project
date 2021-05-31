package edu.epam.project.dao;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    boolean add(Movie movie) throws DaoException;

    boolean deleteMovieByTitle(String title) throws DaoException;

    boolean updateMoviePosterByMovieId(String picturePath, long movieId) throws DaoException;

    int countMovies() throws DaoException;

    Optional<Movie> findMoviePosterByMovieId(long movieId) throws DaoException;

    List<Movie> findAll() throws DaoException;

    List<Movie> findAllCurrentYearMovies() throws DaoException;

    List<Integer> findAllMovieYears() throws DaoException;

    List<Movie> findMoviesByYear(int year) throws DaoException;

    List<Movie> findMoviesByGenre(Genre genre) throws DaoException;

    List<Movie> findMoviesByGenreAndYear(Genre genre, int year) throws DaoException;

    List<Genre> findMovieGenresByMovieId(long movieId) throws DaoException;

    List<Movie> findNewestMovies() throws DaoException;

    List<Movie> findMostRatedMovies() throws DaoException;

    Optional<Movie> findMovieByTitle(String title) throws DaoException;

    Optional<Movie> findMovieById(long id) throws DaoException;

    List<Movie> findRatedMoviesByUserName(String userName) throws DaoException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws DaoException;

    List<Movie> findLatestUploadedMovies() throws DaoException;

    List<Movie> findLatestReviewedMovies() throws DaoException;

    boolean addActor(Actor actor) throws DaoException;

    boolean addActorToMovieByMovieId(Actor actor, long movieId) throws DaoException;

    boolean removeActorByFirstName(String firstName) throws DaoException;

    boolean removeActorFromMovieById(long actorId, long movieId) throws DaoException;

    boolean isActorAlreadyExists(String firstName, String lastName) throws DaoException;

    List<Actor> findActorsByMovieId(long movieId) throws DaoException;

    Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws DaoException;

    boolean addDirector(Director director) throws DaoException;

    boolean removeDirectorFromMovie(long directorId, long movieId) throws DaoException;

    boolean isDirectorAlreadyExists(Director director) throws DaoException;

    boolean addDirectorToMovieByMovieId(Director director, long movieId) throws DaoException;

    Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws DaoException;

    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;

    boolean addGenre(Genre genre) throws DaoException;

    boolean addGenreToMovie(long genreId, long movieId) throws DaoException;

    boolean removeGenreFromMovieByMovieAndGenreId(long movieId, long genreId) throws DaoException;

    List<Genre> findAllGenres() throws DaoException;
}
