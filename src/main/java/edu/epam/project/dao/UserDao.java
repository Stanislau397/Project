package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean register(User user, String password) throws DaoException;

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    long findUserIdByUserName(String userName) throws DaoException;

    Optional<User> findUserByUserName(String userName) throws DaoException;

    boolean changePassword(User user, String password, String newPassword) throws DaoException;

    boolean changeUserName(String userName, String newUserName) throws DaoException;

    boolean updateUserStatusByUserName(String userName, boolean status) throws DaoException;

    boolean changeUserRoleByUserName(String userName, String role) throws DaoException;

    List<User> findAll() throws DaoException;

    List<User> findLatestRegisteredUsers() throws DaoException;
}
