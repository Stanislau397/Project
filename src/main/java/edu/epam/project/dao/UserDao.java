package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean register(User user, String password) throws DaoException;

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    long findUserIdByUserName(String userName) throws DaoException;

    boolean changePassword(User user, String password, String newPassword) throws DaoException;

    boolean changeEmail(String email, String newEmail) throws DaoException;

    boolean changeUserName(String userName, String newUserName) throws DaoException;

    List<User> findAll() throws DaoException;
}
