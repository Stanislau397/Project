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

    Optional<Movie> findMovieById(long movieId) throws ServiceException;

    List<Movie> findRatedMoviesByUserName(String userName) throws ServiceException;

    boolean addActor(Actor actor) throws ServiceException;

    boolean removeActorByFirstName(String firstName) throws ServiceException;

    List<Actor> findActorsByMovieId(long movieId) throws ServiceException;

    boolean addDirector(Director director) throws ServiceException;

    List<Director> findDirectorsByMovieId(long movieId) throws ServiceException;

    boolean addGenre(Genre genre) throws ServiceException;
}
