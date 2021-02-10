package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.DirectorDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.entity.Director;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorDaoIml implements DirectorDao {

    private static final Logger logger = LogManager.getLogger(DirectorDaoIml.class);

    @Override
    public boolean add(Director director) throws DaoException {
        boolean isAdded;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_DIRECTOR)) {
            statement.setLong(1, director.getDirectorId());
            statement.setString(2, director.getFirstName());
            statement.setString(3, director.getLastName());
            int update = statement.executeUpdate();
            isAdded = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isAdded;
    }

    @Override
    public List<Director> findDirectorsByMovieId(long movieId) throws DaoException {
        List<Director> directors = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.FIND_DIRECTORS_BY_MOVIE_ID)) {
            statement.setLong(1, movieId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long directorId = resultSet.getLong(TableColumn.DIRECTOR_ID);
                String firstName = resultSet.getString(TableColumn.FIRST_NAME);
                String lastName = resultSet.getString(TableColumn.LAST_NAME);
                Director director = new Director(directorId, firstName, lastName);
                directors.add(director);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return directors;
    }
}
