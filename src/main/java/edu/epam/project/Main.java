package edu.epam.project;

import edu.epam.project.dao.MovieDao;
import edu.epam.project.dao.impl.MovieDaoImpl;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.DaoException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.core.util.ArrayUtils;

import java.sql.Date;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    private static final String DIRECTORY_PATH = "C:/project/src/main/webapp/css/image/js.jsp";

    public static void main(String[] args) throws ServiceException, DaoException {
        MovieService movieService = new MovieServiceImpl();
        MovieDao movieDao = new MovieDaoImpl();
        List<Movie> comingMovies = movieDao.findCurrentYearMoviesByGenreTitle("Action");
        System.out.println(comingMovies);
        //List<Movie> moviesByGenre = findUpcomingMoviesByGenre(comingMovies, "Comedy");
        //System.out.println(moviesByGenre);
    }

    static List<Movie> findUpcomingMoviesByGenre(List<Movie> upcomingMovies, String genre) {
        List<Movie> moviesByGenre = new ArrayList<>();
        for (Movie movie : upcomingMovies) {
            if (movie.getGenre().getGenreTitle().equals(genre)) {
                moviesByGenre.add(movie);
            }
        }
        return moviesByGenre;
    }
}
