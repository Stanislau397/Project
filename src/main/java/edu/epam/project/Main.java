package edu.epam.project;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.dao.impl.RatingDaoImpl;
import edu.epam.project.entity.*;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import edu.epam.project.validator.AccountValidator;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static final String PASSWORD_REGEX = "([^\\\\s]+(\\\\.(?i)(jpe?g|png|gif|bmp))$)";
    static final Pattern REGEX = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|jpeg))$)");
    private static final Pattern RELEASE_DATE_PATTERN = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");

    public static void main(String[] args) throws ServiceException, DaoException {
        MovieDao movieDao = new MovieDaoImpl();
        System.out.println("2021-05-05s".matches(RELEASE_DATE_PATTERN.pattern()));
    }
}
