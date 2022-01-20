package edu.epam.project.dao;

import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean register(User user, String password) throws DaoException; //completed

    boolean changePasswordByIdAndPassword(long userId, String password, String newPassword) throws DaoException; //completed

    boolean updateStatusById(long userId, boolean status) throws DaoException; //completed

    boolean updateAvatarById(long userId, String avatar) throws DaoException; //completed

    boolean changeRoleById(long userId, String role) throws DaoException; //completed

    int countUsers() throws DaoException; //completed

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;  //completed

    Optional<User> findUserByUserName(String userName) throws DaoException; //completed

    List<User> findAll() throws DaoException; //completed

    List<User> findLatestRegisteredUsers() throws DaoException;  //todo
}
