package edu.epam.project.sevice.impl;

import edu.epam.project.dao.UserDao;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.UserService;
import edu.epam.project.util.PasswordEncryptor;
import edu.epam.project.validator.AccountValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    public static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public boolean register(User user, String password) throws ServiceException {
        boolean isRegistered = false;
        AccountValidator validator = new AccountValidator();
        PasswordEncryptor encryptor = new PasswordEncryptor();
        String userEmail = user.getEmail();
        String userName = user.getUserName();
        user.setBlocked(false);
        user.setRole(RoleType.USER);
        user.setPassword(password);
        try {
            if (validator.isValidAccountData(userName, password, userEmail)) {
                String encryptedPassword = encryptor.encryptPassword(password);
                isRegistered = userDao.register(user, encryptedPassword);
                long userId = userDao.findUserIdByUserName(userName);
                user.setUserId(userId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRegistered;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        Optional<User> isFound;
        try {
            String encryptedPassword = encryptor.encryptPassword(password);
            isFound = userDao.findByEmailAndPassword(email, encryptedPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public boolean changeEmail(String email, String newEmail) throws ServiceException {
        AccountValidator validator = new AccountValidator();
        boolean isEmailChanged = false;
        try {
            if (validator.isValidEmail(newEmail)) {
                isEmailChanged = userDao.changeEmail(email, newEmail);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isEmailChanged;
    }

    @Override
    public boolean changePassword(User user, String password, String newPassword) throws ServiceException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        AccountValidator validator = new AccountValidator();
        boolean isPasswordChanged = false;
        try {
            if (validator.isValidPassword(newPassword)) {
                String encryptedPassword = encryptor.encryptPassword(password);
                String encryptedNewPassword = encryptor.encryptPassword(newPassword);
                isPasswordChanged = userDao.changePassword(user, encryptedPassword, encryptedNewPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isPasswordChanged;
    }

    @Override
    public boolean changeUserName(String oldUserName, String newUserName) throws ServiceException {
        boolean isUserNameChanged = false;
        AccountValidator validator = new AccountValidator();
        try {
            if (validator.isValidUserName(newUserName)) {
                isUserNameChanged = userDao.changeUserName(oldUserName, newUserName);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUserNameChanged;
    }

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return users;
    }
}
