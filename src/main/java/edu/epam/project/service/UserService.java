package edu.epam.project.service;

import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean register(User user, String password) throws ServiceException;

    boolean updateEmailAndRoleByUserId(String email, RoleType role, long userId) throws ServiceException;

    Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException;

    Optional<User> findUserByUserName(String userName) throws ServiceException;

    int countAmountOfUsers() throws ServiceException;

    boolean changePassword(User user, String oldPassword, String newPassword, String confirmNewPassword) throws ServiceException;

    boolean changeUserName(String oldUserName, String newUserName) throws ServiceException;

    boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException;

    boolean changeUserRoleByUserName(String userName, String role) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<User> findLatestRegisteredUsers() throws ServiceException;
}
