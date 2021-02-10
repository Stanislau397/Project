package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.ActorDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Actor;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActorDaoImpl implements ActorDao {

    private static final Logger logger = LogManager.getLogger(ActorDaoImpl.class);

    @Override
    public boolean add(Actor actor) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_ACTOR)) {
            statement.setLong(1, actor.getActorId());
            statement.setString(2, actor.getFirstName());
            statement.setString(3, actor.getLastName());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public boolean removeByFirstName(String firstName) throws DaoException {
        boolean isRemoved;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.DELETE_ACTOR_BY_NAME)) {
            statement.setString(1, firstName);
            int update = statement.executeUpdate();
            isRemoved = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRemoved;
    }

    @Override
    public List<Actor> findActorsByMovieId(long movieId) throws DaoException {
        List<Actor> actors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_ACTORS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long actorId = resultSet.getLong(TableColumn.ACTOR_ID);
                String firstName = resultSet.getString(TableColumn.FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.LAST_NAME);
                Actor actor = new Actor(actorId, firstName, lastName);
                actors.add(actor);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return actors;
    }
}
