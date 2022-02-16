package edu.epam.project.service;

import edu.epam.project.entity.User;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import java.util.List;

public interface UserService {

    boolean add(User user, String password) throws ServiceException;

    boolean updatePasswordByIdAndPassword(long userId, String oldPassword, String newPassword) throws ServiceException, InvalidInputException;

    boolean updateStatusById(long userId, boolean status) throws ServiceException;

    boolean updateAvatarById(long userId, String avatar) throws ServiceException;

    boolean updateRoleById(long userId, String role) throws ServiceException;

    boolean existsById(long userId) throws ServiceException;

    boolean existsByUserName(String userName) throws ServiceException;

    boolean existsByEmail(String email) throws ServiceException;

    boolean existsByIdAndPassword(long userId, String password) throws ServiceException;

    User findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException;

    User findByUserName(String userName) throws ServiceException;

    User findById(long userId) throws ServiceException;

    List<User> findAll() throws ServiceException;

    int countUsers() throws ServiceException;
}
