package edu.epam.project.service.impl;

import edu.epam.project.dao.UserDao;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.InvalidInputException;
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
    public boolean add(User user, String password) throws ServiceException {
        boolean isAdded = false;
        AccountValidator validator = new AccountValidator();
        PasswordEncryptor encryptor = new PasswordEncryptor();
        String email = user.getEmail();
        String userName = user.getUserName();
        try {
            boolean userNameExists = existsByUserName(userName);
            boolean emailExists = existsByEmail(email);
            boolean accountValid = validator.isValidAccountData(userName, password, email);
            if (accountValid && !userNameExists && !emailExists) {
                String encryptedPassword = encryptor.encryptPassword(password);
                isAdded = userDao.add(user, encryptedPassword);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateStatusById(long userId, boolean status) throws ServiceException {
        boolean isUpdated = false;
        try {
            boolean userExists = existsById(userId);
            if (userExists) {
                isUpdated = userDao.updateStatusById(userId, status);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAvatarById(long userId, String avatar) throws ServiceException {
        boolean isUpdated = false;
        try {
            boolean userExists = existsById(userId);
            if (userExists) {
                isUpdated = userDao.updateAvatarById(userId, avatar);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateRoleById(long userId, String role) throws ServiceException {
        boolean isRoleUpdated = false;
        try {
            boolean userExists = existsById(userId);
            if (userExists) {
                isRoleUpdated = userDao.updateRoleById(userId, role);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRoleUpdated;
    }

    @Override
    public boolean existsById(long userId) throws ServiceException {
        boolean exists;
        try {
            exists = userDao.existsById(userId);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByUserName(String userName) throws ServiceException {
        boolean exists;
        try {
            exists = userDao.existsByUserName(userName);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByEmail(String email) throws ServiceException {
        boolean exists;
        try {
            exists = userDao.existsByEmail(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public boolean existsByIdAndPassword(long userId, String password) throws ServiceException {
        boolean exists = false;
        AccountValidator accountValidator = new AccountValidator();
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        try {
            boolean passwordValid = accountValidator.isValidPassword(password);
            if (passwordValid) {
                String encryptedPassword = passwordEncryptor.encryptPassword(password);
                exists = userDao.existsByIdAndPassword(userId, encryptedPassword);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        AccountValidator validator = new AccountValidator();
        Optional<User> userOptional;
        User user = User.newUserBuilder().build();
        try {
            boolean emailValid = validator.isValidEmail(email);
            boolean passwordValid = validator.isValidPassword(password);
            if (emailValid && passwordValid) {
                String encryptedPassword = encryptor.encryptPassword(password);
                userOptional = userDao.findByEmailAndPassword(email, encryptedPassword);
                if (userOptional.isPresent()) {
                    user = userOptional.get();
                }
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findByUserName(String userName) throws ServiceException {
        Optional<User> foundUser;
        User user = User.newUserBuilder().build();
        try {
            boolean userExists = existsByUserName(userName);
            if (userExists) {
                foundUser = userDao.findByUserName(userName);
                if (foundUser.isPresent()) {
                    user = foundUser.get();
                }
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findById(long userId) throws ServiceException {
        Optional<User> foundUser;
        User user = User.newUserBuilder().build();
        try {
            boolean userExists = existsById(userId);
            if (userExists) {
                foundUser = userDao.findById(userId);
                if (foundUser.isPresent()) {
                    user = foundUser.get();
                }
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public int countUsers() throws ServiceException {
        int amountOfUsers;
        try {
            amountOfUsers = userDao.countUsers();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return amountOfUsers;
    }

    @Override
    public boolean updatePasswordByIdAndPassword(long userId, String oldPassword, String newPassword) throws ServiceException, InvalidInputException {
        PasswordEncryptor encryptor = new PasswordEncryptor();
        AccountValidator validator = new AccountValidator();
        boolean isPasswordChanged = false;
        try {
            boolean userExists = existsByIdAndPassword(userId, oldPassword);
            boolean newPasswordValid = validator.isValidPassword(newPassword);
            if (userExists && newPasswordValid) {
                String encryptedNewPassword = encryptor.encryptPassword(newPassword);
                isPasswordChanged = userDao.updatePasswordById(userId, encryptedNewPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isPasswordChanged;
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
