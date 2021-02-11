package edu.epam.project.dao;

import edu.epam.project.entity.Comment;
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

    boolean leaveCommentByUserId(long movieId, String userName, String comment) throws DaoException;

    List<Comment> findCommentsByMovieId(long movieId) throws DaoException;

    boolean removeComment(long movieId, long userId, String comment) throws DaoException;
}
