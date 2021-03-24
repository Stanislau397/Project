package edu.epam.project.service;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean add(Movie movie) throws ServiceException;

    boolean deleteMovieByTitle(String title) throws ServiceException;

    Optional<Movie> findMovieByTitle(String title) throws ServiceException;

    List<Movie> findAllMovies() throws ServiceException;

    List<Movie> findAllCurrentYearMovies() throws ServiceException;

    List<Movie> findMoviesByYear(int year) throws ServiceException;

    List<Movie> findMoviesByGenre(Genre genre) throws ServiceException;

    List<Movie> findNewestMovies() throws ServiceException;

    List<Integer> findAllMovieYears() throws ServiceException;

    Optional<Movie> findMovieById(long movieId) throws ServiceException;

    List<Movie> findRatedMoviesByUserName(String userName) throws ServiceException;

    List<Movie> findMoviesByKeyWord(String keyWord) throws ServiceException;

    boolean addActor(Actor actor) throws ServiceException;

    boolean addActorToMovieByMovieId(Actor actor, long movieId) throws ServiceException;

    boolean removeActorByFirstName(String firstName) throws ServiceException;

    boolean isActorAlreadyExists(String firstName, String lastName) throws ServiceException;

    Optional<Actor> findActorByFirstLastName(String firstName, String lastName) throws ServiceException;

    List<Actor> findActorsByMovieId(long movieId) throws ServiceException;

    boolean addDirector(Director director) throws ServiceException;

    boolean addDirectorToMovieByMovieId(Director director, long movieId) throws ServiceException;

    Optional<Director> findDirectorByFirstLastName(String firstName, String lastName) throws ServiceException;

    boolean isDirectorAlreadyExists(Director director) throws ServiceException;

    List<Director> findDirectorsByMovieId(long movieId) throws ServiceException;

    boolean addGenre(Genre genre) throws ServiceException;

    boolean addGenreToMovie(long genreId, long movieId) throws ServiceException;

    List<Genre> findAllGenres() throws ServiceException;
}
