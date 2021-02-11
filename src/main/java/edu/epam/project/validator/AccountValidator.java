package edu.epam.project.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AccountValidator {

    public static final Logger logger = LogManager.getLogger(AccountValidator.class);
    private static final String PASSWORD_REGEX = "^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$";
    private static final String USER_NAME_REGEX = "^[a-zA-Z0-9](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){6,18}[a-zA-Z0-9]$";
    private static final String EMAIL_REGEX = "^[^@]+@[^@]+\\.[^@]+$";

    public boolean isValidLogInData(String login, String password) {
        boolean isCorrectLogIn = isValidUserName(login) && isValidPassword(password);
        if (!(isCorrectLogIn)) {
            logger.log(Level.WARN, "Invalid login Data"); /// TODO: 11.02.2021
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
        if (!(isUserNameCorrect)) {
            logger.log(Level.WARN, "Invalid userName Data");
        }
        return isUserNameCorrect;
    }

    public boolean isValidEmail(String email) {
        boolean isEmailCorrect = email.matches(EMAIL_REGEX);
        if (!(isEmailCorrect)) {
            logger.log(Level.WARN, "Invalid email Data");
        }
        return isEmailCorrect;
    }

    public boolean isValidPassword(String password) {
        int minLength = 7;
        int maxLength = 25;
        boolean isPasswordCorrect = password.matches(PASSWORD_REGEX)
                && password.length() > minLength && password.length() < maxLength;
        if (!(isPasswordCorrect)) {
            logger.log(Level.WARN, "Invalid password Data");
        }
        return isPasswordCorrect;
    }

}
