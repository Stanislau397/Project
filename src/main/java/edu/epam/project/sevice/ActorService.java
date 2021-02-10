package edu.epam.project.sevice;

import edu.epam.project.entity.Actor;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface ActorService {

    boolean add(Actor actor) throws ServiceException;
    boolean removeByFirstName(String firstName) throws ServiceException;
    List<Actor> findActorsByMovieId(long movieId) throws ServiceException;
}
