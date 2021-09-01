package edu.epam.project.validator;

import java.util.regex.Pattern;

public class MovieValidator {

    private static final Pattern RELEASE_DATE_PATTERN = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
    private static final Pattern RUN_TIME_PATTERN = Pattern.compile("^\\d+$");
    private static final Pattern DESCRIPTION_PATTERN = Pattern.compile("^.{1,800}$");
    private static final Pattern TITLE_PATTERN = Pattern.compile("^.{1,80}$");

    public boolean isTitleValid(String title) {
        return title.matches(TITLE_PATTERN.pattern());
    }

    public boolean isReleaseDateValid(String releaseDate) {
        return releaseDate.matches(RELEASE_DATE_PATTERN.pattern());
    }

    public boolean isRunTimeValid(int runTime) {
        return String.valueOf(runTime).matches(RUN_TIME_PATTERN.pattern());
    }

    public boolean isDescriptionValid(String description) {
        return description.matches(DESCRIPTION_PATTERN.pattern());
    }

}
