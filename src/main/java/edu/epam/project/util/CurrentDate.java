package edu.epam.project.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

    private static final String DATE_PATTERN = "dd-MM-yyyy 'at' HH:mm";

    public String getCurrentDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        String currentDate = simpleDateFormat.format(date);
        return currentDate;
    }
}
