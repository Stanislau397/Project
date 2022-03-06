package edu.epam.project.service.impl;

import edu.epam.project.dao.UserDao;
import edu.epam.project.dao.impl.UserDaoImpl;
import edu.epam.project.entity.User;
import edu.epam.project.exception.AlreadyExistsException;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.util.FileUploader;
import edu.epam.project.util.PasswordEncryptor;
import edu.epam.project.validator.AccountValidator;
import edu.epam.project.validator.ServiceValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final String DIRECTORY_PATH_OF_IMAGE = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/avatar/";
    private static final String LOCALHOST_PATH_OF_IMAGE = "http://localhost:8080/image/avatar/";
    private static final String USER_NAME_EXISTS_MESSAGE = "This username already taken";
    private static final String EMAIL_EXISTS_MESSAGE = "This email already taken";
    private static final String SLASH = "/";
    private static UserDao userDao = new UserDaoImpl();

    @Override
    public boolean add(User user, String password) throws ServiceException, AlreadyExistsException {
        boolean isAdded = false;
        String email = user.getEmail();
        String userName = user.getUserName();
        try {
            boolean userNameExists = existsByUserName(userName);
            boolean emailExists = existsByEmail(email);
            boolean accountValid = ServiceValidator.isValidAccountData(userName, password, email);
            if (userNameExists) {
                throw new AlreadyExistsException(USER_NAME_EXISTS_MESSAGE);
            }
            if (emailExists) {
                throw new AlreadyExistsException(EMAIL_EXISTS_MESSAGE);
            }
            if (accountValid) {
                String encryptedPassword = PasswordEncryptor.encryptPassword(password);
                isAdded = userDao.add(user, encryptedPassword);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e.getMessage());
        }
        return isAdded;
    }

    @Override
    public boolean updateStatusByUserId(long userId, boolean status) throws ServiceException {
        boolean isUpdated = false;
        try {
            boolean userExists = existsByUserId(userId);
            if (userExists) {
                isUpdated = userDao.updateStatusByUserId(userId, status);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateAvatarByUserId(long userId, Part part) throws ServiceException {
        boolean isUpdated = false;
        String imageName = part.getSubmittedFileName();
        try {
            boolean userExists = existsByUserId(userId);
            boolean avatarValid = ServiceValidator.isImageValid(imageName);
            if (userExists && avatarValid) {
                String imagePath = FileUploader.save(part, DIRECTORY_PATH_OF_IMAGE, imageName);
                int lastIndexOfSlash = imagePath.lastIndexOf(SLASH);
                imageName = imagePath.substring(lastIndexOfSlash + 1);
                String imagePathForDb = LOCALHOST_PATH_OF_IMAGE.concat(imageName);
                isUpdated = userDao.updateAvatarByUserId(userId, imagePathForDb);
            }
        } catch (DaoException | InvalidInputException | IOException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isUpdated;
    }

    @Override
    public boolean updateRoleByUserId(long userId, String role) throws ServiceException {
        boolean isRoleUpdated = false;
        try {
            boolean userExists = existsByUserId(userId);
            if (userExists) {
                isRoleUpdated = userDao.updateRoleByUserId(userId, role);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return isRoleUpdated;
    }

    @Override
    public boolean existsByUserId(long userId) throws ServiceException {
        boolean exists;
        try {
            exists = userDao.existsByUserId(userId);
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
    public boolean existsByUserIdAndPassword(long userId, String password) throws ServiceException {
        boolean exists = false;
        try {
            boolean passwordValid = ServiceValidator.isValidPassword(password);
            if (passwordValid) {
                String encryptedPassword = PasswordEncryptor.encryptPassword(password);
                exists = userDao.existsByUserIdAndPassword(userId, encryptedPassword);
            }
        } catch (DaoException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return exists;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws ServiceException, InvalidInputException {
        AccountValidator validator = new AccountValidator();
        Optional<User> userOptional;
        User user = User.newUserBuilder().build();
        try {
            boolean emailValid = validator.isValidEmail(email);
            boolean passwordValid = validator.isValidPassword(password);
            if (emailValid && passwordValid) {
                String encryptedPassword = PasswordEncryptor.encryptPassword(password);
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
        User user = User.newUserBuilder().build();
        Optional<User> foundUser;
        try {
            foundUser = userDao.findByUserName(userName);
            if (foundUser.isPresent()) {
                user = foundUser.get();
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public User findByUserId(long userId) throws ServiceException {
        Optional<User> foundUser;
        User user = User.newUserBuilder().build();
        try {
            foundUser = userDao.findByUserId(userId);
            if (foundUser.isPresent()) {
                user = foundUser.get();

            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e);
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updatePasswordByUserIdAndPassword(long userId, String oldPassword, String newPassword) throws ServiceException, InvalidInputException {
        boolean isPasswordChanged = false;
        try {
            boolean userExists = existsByUserIdAndPassword(userId, oldPassword);
            boolean newPasswordValid = ServiceValidator.isValidPassword(newPassword);
            if (userExists && newPasswordValid) {
                String encryptedNewPassword = PasswordEncryptor.encryptPassword(newPassword);
                isPasswordChanged = userDao.updatePasswordByUserId(userId, encryptedNewPassword);
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
}
