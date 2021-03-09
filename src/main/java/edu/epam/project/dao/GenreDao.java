package edu.epam.project.dao;

import edu.epam.project.entity.Genre;
import edu.epam.project.exception.DaoException;

import java.util.Optional;

public interface GenreDao {

    boolean add(Genre genre) throws DaoException;

    boolean removeById(long genreId) throws DaoException;

    boolean addGenreToMovieById(long genreId, long movieId) throws DaoException;

    Optional<Genre> findMovieGenreByMovieId(long movieId) throws DaoException;
}
