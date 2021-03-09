package edu.epam.project.dao;

import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface MovieDao {

    boolean add(Movie movie) throws DaoException;

    boolean deleteMovieByTitle(String title) throws DaoException;

    List<Movie> findAll() throws DaoException;

    Optional<Movie> findMovieByTitle(String title) throws DaoException;

    Optional<Movie> findMovieById(long id) throws DaoException;

    List<Movie> findRatedMoviesByUserName(String userName) throws DaoException;

    boolean addActor(Actor actor) throws DaoException;

    boolean removeActorByFirstName(String firstName) throws DaoException;

    List<Actor> findActorsByMovieId(long movieId) throws DaoException;

    boolean addDirector(Director director) throws DaoException;

    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;
}
