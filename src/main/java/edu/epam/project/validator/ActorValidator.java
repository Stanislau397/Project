package edu.epam.project.validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActorValidator {

    private static final Logger logger = LogManager.getLogger(ActorValidator.class);
    private static final String FIRST_NAME_REGEX = "(?i)[a-z]([- ',.a-z]{0,23}[a-z])?";
    private static final String LAST_NAME_REGEX = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$";
    private static final String BIRTH_DATE_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    private static final String INVALID_FIRST_NAME_MSG = "Invalid first name data";
    private static final String INVALID_LAST_NAME_MSG = "Invalid last name data";
    private static final String INVALID_BIRTH_DATE_MSG = "Invalid birth date data";

    public boolean isValidFirstName(String firstName) {
        boolean isFirstNameValid = firstName.matches(FIRST_NAME_REGEX);
        if (!isFirstNameValid) {
            logger.log(Level.INFO, INVALID_FIRST_NAME_MSG);
        }
        return isFirstNameValid;
    }

    public boolean isValidLastName(String lastName) {
        boolean isLastNameValid = lastName.matches(LAST_NAME_REGEX);
        if (!isLastNameValid) {
            logger.log(Level.INFO, INVALID_LAST_NAME_MSG);
        }
        return isLastNameValid;
    }

    public boolean isValidBirthDate(String birthDate) {
        boolean isBirthDateValid = birthDate.matches(BIRTH_DATE_REGEX);
        if (!isBirthDateValid) {
            logger.log(Level.INFO, INVALID_BIRTH_DATE_MSG);
        }
        return isBirthDateValid;
    }
}
