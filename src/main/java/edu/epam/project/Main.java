package edu.epam.project;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.RatingDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.dao.impl.RatingDaoImpl;
import edu.epam.project.entity.*;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.parser.ActorParser;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static final String DIRECTORY_PATH = "C:/project/src/main/webapp/css/image/js.jsp";

    public static void main(String[] args) throws ServiceException, DaoException {
        MovieService movieService = new MovieServiceImpl();
        List<Country> countries = movieService.findAllCountries();
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    private static boolean isGenreAlreadyExists(String genreTitle) throws DaoException {
        MovieDao movieDao = new MovieDaoImpl();
        Optional<Genre> genreOptional = movieDao.findGenreByTitle(genreTitle);
        return genreOptional.isPresent();
    }
}
