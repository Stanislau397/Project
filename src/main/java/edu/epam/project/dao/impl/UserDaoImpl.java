package edu.epam.project.dao.impl;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.dao.SqlQuery;
import edu.epam.project.dao.TableColumn;
import edu.epam.project.dao.UserDao;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    public static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean register(User user, String password) throws DaoException {
        boolean isRegistered;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, password);
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setBoolean(5, user.getStatus());
            statement.setString(6, user.getAvatar());
            int result = statement.executeUpdate();
            isRegistered = (result == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRegistered;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> isFound;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_EMAIL_AND_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            User user = User.newUserBuilder()
                    .withUserId(resultSet.getLong(TableColumn.USER_ID))
                    .withUserName(resultSet.getString(TableColumn.USER_NAME))
                    .withEmail(resultSet.getString(TableColumn.USER_EMAIL))
                    .withRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)))
                    .withAvatar(resultSet.getString(TableColumn.AVATAR))
                    .withStatus(resultSet.getBoolean(TableColumn.USER_STATUS))
                    .build();
            isFound = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public boolean updateStatusById(long userId, boolean status) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_STATUS)) {
            statement.setBoolean(1, status);
            statement.setLong(2, userId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAvatarById(long userId, String avatar) throws DaoException {
        boolean isUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_AVATAR)) {
            statement.setString(1, avatar);
            statement.setLong(2, userId);
            int update = statement.executeUpdate();
            isUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean changeRoleById(long userId, String role) throws DaoException {
        boolean isRoleUpdated;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_USER_ROLE)) {
            statement.setString(1, role);
            statement.setLong(2, userId);
            int update = statement.executeUpdate();
            isRoleUpdated = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isRoleUpdated;
    }

    @Override
    public int countUsers() throws DaoException {
        int amountOfUsers = 0;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.COUNT_ALL_USERS);
            if (resultSet.next()) {
                amountOfUsers = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return amountOfUsers;
    }

    @Override
    public Optional<User> findUserByUserName(String userName) throws DaoException {
        Optional<User> isFound = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_USER_NAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = User.newUserBuilder()
                        .withUserId(resultSet.getLong(TableColumn.USER_ID))
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .withEmail(resultSet.getString(TableColumn.USER_EMAIL))
                        .withRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)))
                        .withAvatar(resultSet.getString(TableColumn.AVATAR))
                        .withStatus(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                isFound = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public boolean changePasswordByIdAndPassword(long userId, String password, String newPassword) throws DaoException {
        boolean isPasswordChanged;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.CHANGE_PASSWORD)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
            statement.setString(3, password);
            int update = statement.executeUpdate();
            isPasswordChanged = (update == 1);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isPasswordChanged;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = User.newUserBuilder()
                        .withUserId(resultSet.getLong(TableColumn.USER_ID))
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .withEmail(resultSet.getString(TableColumn.USER_EMAIL))
                        .withRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)))
                        .withStatus(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                allUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allUsers;
    }

    @Override
    public List<User> findLatestRegisteredUsers() throws DaoException {
        List<User> latestUsers = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LATEST_USERS);
            while (resultSet.next()) {
                User user = User.newUserBuilder()
                        .withUserId(resultSet.getLong(TableColumn.USER_ID))
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .withEmail(resultSet.getString(TableColumn.USER_EMAIL))
                        .withRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)))
                        .withStatus(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                latestUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return latestUsers;
    }
}
