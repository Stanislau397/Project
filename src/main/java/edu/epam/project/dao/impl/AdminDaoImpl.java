package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.AdminDao;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDaoImpl implements AdminDao {

    private static final Logger logger = LogManager.getLogger(AdminDaoImpl.class);

    @Override
    public boolean updateUserStatusByUserName(String userName, boolean status) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setBoolean(1, status);
            statement.setString(2, userName);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }
}
