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
    public boolean add(User user, String password) throws DaoException {
        boolean isRegistered;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_TO_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, password);
            statement.setString(3, user.getEmail());
            statement.setString(4, String.valueOf(user.getRole()));
            statement.setBoolean(5, user.getIsLocked());
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
                    .withIsLocked(resultSet.getBoolean(TableColumn.USER_STATUS))
                    .build();
            isFound = Optional.of(user);
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return isFound;
    }

    @Override
    public boolean updateStatusByUserId(long userId, boolean status) throws DaoException {
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
    public boolean updateAvatarByUserId(long userId, String avatar) throws DaoException {
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
    public boolean updateRoleByUserId(long userId, String role) throws DaoException {
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
    public boolean existsByUserName(String userName) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_USER_NAME)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByUserId(long userId) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByEmail(String email) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByUserIdAndPassword(long userId, String password) throws DaoException {
        boolean exists = false;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID_AND_PASSWORD)) {
            statement.setLong(1, userId);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return exists;
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
    public Optional<User> findByUserName(String userName) throws DaoException {
        Optional<User> foundUser = Optional.empty();
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
                        .withIsLocked(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                foundUser = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return foundUser;
    }

    @Override
    public Optional<User> findByUserId(long userId) throws DaoException {
        Optional<User> foundUser = Optional.empty();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USER_BY_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = User.newUserBuilder()
                        .withUserId(resultSet.getLong(TableColumn.USER_ID))
                        .withUserName(resultSet.getString(TableColumn.USER_NAME))
                        .withEmail(resultSet.getString(TableColumn.USER_EMAIL))
                        .withRole(RoleType.valueOf(resultSet.getString(TableColumn.USER_ROLE)))
                        .withAvatar(resultSet.getString(TableColumn.AVATAR))
                        .withIsLocked(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                foundUser = Optional.of(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return foundUser;
    }

    @Override
    public boolean updatePasswordByUserId(long userId, String newPassword) throws DaoException {
        boolean isPasswordChanged;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD_BY_ID)) {
            statement.setString(1, newPassword);
            statement.setLong(2, userId);
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
                        .withIsLocked(resultSet.getBoolean(TableColumn.USER_STATUS))
                        .build();
                allUsers.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e);
            throw new DaoException(e);
        }
        return allUsers;
    }
}
