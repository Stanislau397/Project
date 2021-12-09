package edu.epam.project.service;

import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean register(User user, String password) throws ServiceException, InvalidInputException;

    boolean updateEmailAndRoleByUserId(String email, RoleType role, long userId) throws ServiceException, InvalidInputException;

    Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException;

    Optional<User> findUserByUserName(String userName) throws ServiceException;

    int countAmountOfUsers() throws ServiceException;

    boolean changePassword(User user, String oldPassword, String newPassword, String confirmNewPassword) throws ServiceException, InvalidInputException;

    boolean changeUserName(String oldUserName, String newUserName) throws ServiceException, InvalidInputException;

    boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException;

    boolean updateUserAvatarById(long userId, String avatar) throws ServiceException;

    boolean changeUserRoleByUserName(String userName, String role) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findLatestRegisteredUsers() throws ServiceException;
}
