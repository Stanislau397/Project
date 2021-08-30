package edu.epam.project.service.impl;

import edu.epam.project.dao.UserDao;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
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
    public boolean updateEmailAndRoleByUserId(String email, RoleType role, long userId) throws ServiceException {
        boolean isUpdated = false;
        AccountValidator validator = new AccountValidator();
        try {
            if (validator.isValidEmail(email)) {
                isUpdated = userDao.updateEmailAndRoleByUserId(email, role, userId);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserStatusByUserName(boolean status, String userName) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updateUserStatusByUserName(userName, status);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateUserAvatarById(long userId, String avatar) throws ServiceException {
        boolean isUpdated;
        try {
            isUpdated = userDao.updateUserAvatarById(userId, avatar);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean changeUserRoleByUserName(String userName, String role) throws ServiceException {
        boolean isRoleUpdated;
        try {
            isRoleUpdated = userDao.changeUserRoleByUserName(userName, role);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRoleUpdated;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws ServiceException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        AccountValidator validator = new AccountValidator();
        Optional<User> isFound = Optional.empty();
        try {
            if (validator.isValidEmail(email) && validator.isValidPassword(password)) {
                String encryptedPassword = encryptor.encryptPassword(password);
                isFound = userDao.findByEmailAndPassword(email, encryptedPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public Optional<User> findUserByUserName(String userName) throws ServiceException {
        Optional<User> isFound;
        try {
            isFound = userDao.findUserByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isFound;
    }

    @Override
    public int countAmountOfUsers() throws ServiceException {
        int amountOfUsers;
        try {
            amountOfUsers = userDao.countAmountOfUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfUsers;
    }

    @Override
    public boolean changePassword(User user, String password, String newPassword, String confirmNewPassword) throws ServiceException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        AccountValidator validator = new AccountValidator();
        boolean isPasswordChanged = false;
        try {
            if (validator.isValidPassword(newPassword) && validator.isValidPassword(confirmNewPassword)
                    && newPassword.equals(confirmNewPassword)) {

                String encryptedPassword = encryptor.encryptPassword(password);
                String encryptedNewPassword = encryptor.encryptPassword(newPassword);
                isPasswordChanged = userDao.changePassword(user, encryptedPassword, encryptedNewPassword, confirmNewPassword);
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

    @Override
    public List<User> findLatestRegisteredUsers() throws ServiceException {
        List<User> latestUsers;
        try {
            latestUsers = userDao.findLatestRegisteredUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return latestUsers;
    }
}
