package edu.epam.project.sevice;

import edu.epam.project.entity.Genre;
import edu.epam.project.exception.ServiceException;

import java.util.Optional;

public interface GenreService {

    boolean add(Genre genre) throws ServiceException;
    boolean removeById(long genreId ) throws ServiceException;
    boolean addGenreToMovieById(long genreId, long movieId) throws ServiceException;
    Optional<Genre> findMovieGenreByMovieId(long movieId) throws ServiceException;
}
