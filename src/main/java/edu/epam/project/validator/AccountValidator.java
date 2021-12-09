package edu.epam.project.validator;

import edu.epam.project.exception.InvalidInputException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountValidator {

    public static final Logger logger = LogManager.getLogger(AccountValidator.class);
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}";
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
    private static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.[^@]+$";
    private static final int MAX_PASSWORD_LENGTH = 25;

    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password Data";
    private static final String INVALID_USER_NAME_MESSAGE = "Invalid user name Data";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid email Data";

    public boolean isValidAccountData(String userName, String password, String email) throws InvalidInputException {
        boolean isUserName = isValidUserName(userName);
        boolean isEmail = isValidEmail(email);
        boolean isPassword = isValidPassword(password);

        return (isPassword && isEmail & isUserName);
    }

    public boolean isValidUserName(String userName) throws InvalidInputException {
        if (!userName.matches(USER_NAME_REGEX)) {
            logger.log(Level.INFO, INVALID_USER_NAME_MESSAGE);
            throw new InvalidInputException(INVALID_USER_NAME_MESSAGE);
        }
        return true;
    }

    public boolean isValidEmail(String email) throws InvalidInputException {
        if (!email.matches(EMAIL_REGEX)) {
            logger.log(Level.INFO, INVALID_EMAIL_MESSAGE);
            throw new InvalidInputException(INVALID_USER_NAME_MESSAGE);
        }
        return true;
    }

    public boolean isValidPassword(String password) throws InvalidInputException {
        if (!password.matches(PASSWORD_REGEX) || (password.length() > MAX_PASSWORD_LENGTH)) {
            logger.log(Level.INFO, INVALID_PASSWORD_MESSAGE);
            throw new InvalidInputException(INVALID_PASSWORD_MESSAGE);
        }
        return true;
    }
}
