package edu.epam.project.dao;

import edu.epam.project.entity.Actor;
import edu.epam.project.exception.DaoException;

import java.util.List;

public interface ActorDao {

    boolean add(Actor actor) throws DaoException;
    boolean removeByFirstName(String firstName) throws DaoException;
    List<Actor> findActorsByMovieId(long movieId) throws DaoException;
}
