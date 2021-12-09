package edu.epam.project.validator;

import edu.epam.project.exception.InvalidInputException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class MovieValidator {

    private static final Logger logger = LogManager.getLogger(MovieValidator.class);
    private static final Pattern IMAGE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)");
    private static final Pattern RELEASE_DATE_PATTERN = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
    private static final Pattern RUN_TIME_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^.{1,1500}$");
    private static final Pattern TITLE_PATTERN = Pattern.compile("^.{1,80}$");

    private static final String INVALID_IMAGE_MSG = "Invalid image data!";
    private static final String INVALID_TITLE_MSG = "Invalid title data!";
    private static final String INVALID_RELEASE_DATE_MSG = "Invalid release date data!";
    private static final String INVALID_DESCRIPTION_MSG = "Invalid description data!";
    private static final String INVALID_RUN_TIME_MSG = "Invalid run time data!";

    public boolean isImageValid(String imagePath) throws InvalidInputException {
        if (!imagePath.matches(IMAGE_PATTERN.pattern())) {
            logger.log(Level.ERROR, INVALID_IMAGE_MSG);
            throw new InvalidInputException(INVALID_IMAGE_MSG);
        }
        return true;
    }

    public boolean isTitleValid(String title) throws InvalidInputException {
        if (!title.matches(TITLE_PATTERN.pattern())) {
            logger.log(Level.ERROR, INVALID_TITLE_MSG);
            throw new InvalidInputException(INVALID_TITLE_MSG);
        }
        return true;
    }

    public boolean isReleaseDateValid(String releaseDate) throws InvalidInputException {
        if (!releaseDate.matches(RELEASE_DATE_PATTERN.pattern())) {
            logger.log(Level.ERROR, INVALID_RELEASE_DATE_MSG);
            throw new InvalidInputException(INVALID_RELEASE_DATE_MSG);
        }
        return true;
    }

    public boolean isRunTimeValid(int runTime) throws InvalidInputException {
        if (!String.valueOf(runTime).matches(RUN_TIME_PATTERN.pattern())) {
            logger.log(Level.ERROR, INVALID_RUN_TIME_MSG);
            throw new InvalidInputException(INVALID_RUN_TIME_MSG);
        }
        return true;
    }

    public boolean isDescriptionValid(String description) throws InvalidInputException {
        if (!description.matches(DESCRIPTION_PATTERN.pattern())) {
            logger.log(Level.ERROR, INVALID_DESCRIPTION_MSG);
            throw new InvalidInputException(INVALID_DESCRIPTION_MSG);
        }
        return true;
    }
}
