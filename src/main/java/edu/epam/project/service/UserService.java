package edu.epam.project.service;

import edu.epam.project.entity.User;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean register(User user, String password) throws ServiceException, InvalidInputException;

    Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException;

    Optional<User> findUserByUserName(String userName) throws ServiceException;

    int countAmountOfUsers() throws ServiceException;

    boolean changePassword(long userId, String oldPassword, String newPassword, String confirmNewPassword) throws ServiceException, InvalidInputException;

    boolean updateStatusById(boolean status, long userId) throws ServiceException;

    boolean updateAvatarById(long userId, String avatar) throws ServiceException;

    boolean changeRoleById(long userId, String role) throws ServiceException;

    List<User> findAll() throws ServiceException;
}
