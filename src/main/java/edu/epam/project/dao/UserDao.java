package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean add(User user, String password) throws DaoException; //completed

    boolean updatePasswordById(long userId, String newPassword) throws DaoException; //completed

    boolean updateStatusById(long userId, boolean status) throws DaoException; //completed

    boolean updateAvatarById(long userId, String avatar) throws DaoException; //completed

    boolean updateRoleById(long userId, String role) throws DaoException; //completed

    boolean existsByUserName(String userName) throws DaoException;

    boolean existsById(long userId) throws DaoException;

    boolean existsByEmail(String email) throws DaoException;

    boolean existsByIdAndPassword(long userId, String password) throws DaoException;

    int countUsers() throws DaoException; //completed

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;  //completed

    Optional<User> findByUserName(String userName) throws DaoException; //completed

    Optional<User> findById(long userId) throws DaoException;

    List<User> findAll() throws DaoException; //completed
}
