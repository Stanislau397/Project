package edu.epam.project.service;

import edu.epam.project.entity.Director;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface DirectorService {

    boolean add(Director director) throws ServiceException;
    List<Director> findDirectorsByMovieId(long movieId) throws ServiceException;
}
