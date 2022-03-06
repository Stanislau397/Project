package edu.epam.project.service;

import edu.epam.project.entity.User;
import edu.epam.project.exception.AlreadyExistsException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

public interface UserService {

    boolean add(User user, String password) throws ServiceException, AlreadyExistsException;

    boolean updatePasswordByUserIdAndPassword(long userId, String oldPassword, String newPassword) throws ServiceException, InvalidInputException;

    boolean updateStatusByUserId(long userId, boolean status) throws ServiceException;

    boolean updateAvatarByUserId(long userId, Part part) throws ServiceException;

    boolean updateRoleByUserId(long userId, String role) throws ServiceException;

    boolean existsByUserId(long userId) throws ServiceException;

    boolean existsByUserName(String userName) throws ServiceException;

    boolean existsByEmail(String email) throws ServiceException;

    boolean existsByUserIdAndPassword(long userId, String password) throws ServiceException;

    User findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException;

    User findByUserName(String userName) throws ServiceException;

    User findByUserId(long userId) throws ServiceException;

    List<User> findAll() throws ServiceException;

    int countUsers() throws ServiceException;
}
