package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean add(User user, String password) throws DaoException;

    boolean updatePasswordByUserId(long userId, String newPassword) throws DaoException;

    boolean updateStatusByUserId(long userId, boolean status) throws DaoException;

    boolean updateAvatarByUserId(long userId, String avatar) throws DaoException;

    boolean updateRoleByUserId(long userId, String role) throws DaoException;

    boolean existsByUserName(String userName) throws DaoException;

    boolean existsByUserId(long userId) throws DaoException;

    boolean existsByEmail(String email) throws DaoException;

    boolean existsByUserIdAndPassword(long userId, String password) throws DaoException;

    int countUsers() throws DaoException;

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    Optional<User> findByUserName(String userName) throws DaoException;

    Optional<User> findByUserId(long userId) throws DaoException;

    List<User> findAll() throws DaoException;
}
