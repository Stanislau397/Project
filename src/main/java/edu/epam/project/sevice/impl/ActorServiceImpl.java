package edu.epam.project.sevice.impl;

import edu.epam.project.dao.ActorDao;
import edu.epam.project.dao.impl.ActorDaoImpl;
import edu.epam.project.entity.Actor;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.ActorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ActorServiceImpl implements ActorService {

    private static final Logger logger = LogManager.getLogger(ActorServiceImpl.class);
    private ActorDao actorDao = new ActorDaoImpl();

    @Override
    public boolean add(Actor actor) throws ServiceException {
        boolean isAdded;
        try {
            isAdded = actorDao.add(actor);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean removeByFirstName(String firstName) throws ServiceException {
        boolean isRemoved;
        try {
            isRemoved = actorDao.removeByFirstName(firstName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRemoved;
    }

    @Override
    public List<Actor> findActorsByMovieId(long movieId) throws ServiceException {
        List<Actor> actors;
        try {
            actors = actorDao.findActorsByMovieId(movieId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return actors;
    }
}
