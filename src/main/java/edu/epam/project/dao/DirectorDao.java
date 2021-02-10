package edu.epam.project.dao;

import edu.epam.project.entity.Director;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface DirectorDao {

    boolean add(Director director) throws DaoException;
    List<Director> findDirectorsByMovieId(long movieId) throws DaoException;
}
