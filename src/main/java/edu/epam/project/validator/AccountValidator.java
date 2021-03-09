package edu.epam.project.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountValidator {

    public static final Logger logger = LogManager.getLogger(AccountValidator.class);
    private static final String PASSWORD_REGEX = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$";
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
    private static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.[^@]+$";
    private static final String INVALID_LOGIN_MESSAGE = "Invalid login Data";
    private static final String INVALID_PASSWORD_MESSAGE = "Invalid password Data";
    private static final String INVALID_USER_NAME_MESSAGE = "Invalid user name Data";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid email Data";
    private static final int MIN_LENGTH = 7;
    private static final int MAX_LENGTH = 25;

    public boolean isValidLogInData(String login, String password) {
        boolean isCorrectLogIn = isValidUserName(login) && isValidPassword(password);
        if (!isCorrectLogIn) {
            logger.log(Level.INFO, INVALID_LOGIN_MESSAGE);
        }
        return isCorrectLogIn;
    }

    public boolean isValidAccountData(String userName, String password, String email) {
        boolean isUserName = isValidUserName(userName);
        boolean isEmail = isValidEmail(email);
        boolean isPassword = isValidPassword(password);

        return (isPassword && isEmail & isUserName);
    }

    public boolean isValidUserName(String userName) {
        boolean isUserNameCorrect = userName.matches(USER_NAME_REGEX);
        if (!isUserNameCorrect) {
            logger.log(Level.INFO, INVALID_USER_NAME_MESSAGE);
        }
        return isUserNameCorrect;
    }

    public boolean isValidEmail(String email) {
        boolean isEmailCorrect = email.matches(EMAIL_REGEX);
        if (!isEmailCorrect) {
            logger.log(Level.INFO, INVALID_EMAIL_MESSAGE);
        }
        return isEmailCorrect;
    }

    public boolean isValidPassword(String password) {
        boolean isPasswordCorrect = password.matches(PASSWORD_REGEX)
                && password.length() > MIN_LENGTH && password.length() < MAX_LENGTH;
        if (!isPasswordCorrect) {
            logger.log(Level.INFO, INVALID_PASSWORD_MESSAGE);
        }
        return isPasswordCorrect;
    }

}
