package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static edu.epam.project.controller.command.AttributeName.COUNT_ACTORS;
import static edu.epam.project.controller.command.AttributeName.COUNT_DIRECTORS;
import static edu.epam.project.controller.command.AttributeName.COUNT_MOVIES;
import static edu.epam.project.controller.command.AttributeName.COUNT_GENRES;
import static edu.epam.project.controller.command.AttributeName.COUNT_USERS;
import static edu.epam.project.controller.command.AttributeName.MOST_RATED_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.LATEST_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.LATEST_USERS_LIST;
import static edu.epam.project.controller.command.AttributeName.LATEST_REVIEWED_MOVIES_LIST;

public class ToAdminCabinetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToAdminCabinetCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        try {
            if (session.getAttribute("admin") != null) {
                List<Movie> topMovies = movieService.findMostRatedMovies();
                List<Movie> latestMovies = movieService.findLatestUploadedMovies();
                List<Movie> latestReviewedMovies = movieService.findLatestReviewedMovies();
                List<User> latestUsers = userService.findLatestRegisteredUsers();
                int genresAmount = movieService.countGenres();
                int moviesAmount = movieService.countMovies();
                int actorsAmount = movieService.countActors();
                int directorsAmount = movieService.countDirectors();
                int usersAmount = userService.countAmountOfUsers();
                request.setAttribute(MOST_RATED_MOVIES_LIST, topMovies);
                request.setAttribute(LATEST_MOVIES_LIST, latestMovies);
                request.setAttribute(LATEST_USERS_LIST, latestUsers);
                request.setAttribute(LATEST_REVIEWED_MOVIES_LIST, latestReviewedMovies);
                request.setAttribute(COUNT_ACTORS, actorsAmount);
                request.setAttribute(COUNT_DIRECTORS, directorsAmount);
                request.setAttribute(COUNT_USERS, usersAmount);
                request.setAttribute(COUNT_MOVIES, moviesAmount);
                request.setAttribute(COUNT_GENRES, genresAmount);
                router.setPagePath(PagePath.ADMIN_CABINET_PAGE);
            } else {
                router.setPagePath(PagePath.ERROR_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
