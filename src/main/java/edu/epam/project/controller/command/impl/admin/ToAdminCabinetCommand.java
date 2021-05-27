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
import java.io.IOException;
import java.util.List;

public class ToAdminCabinetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToAdminCabinetCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        try {
            List<Movie> topMovies = movieService.findMostRatedMovies();
            List<Movie> latestMovies = movieService.findLatestUploadedMovies();
            List<Movie> latestReviewedMovies = movieService.findLatestReviewedMovies();
            List<User> latestUsers = userService.findLatestRegisteredUsers();
            request.setAttribute(AttributeName.MOST_RATED_MOVIES_LIST, topMovies);
            request.setAttribute(AttributeName.LATEST_MOVIES_LIST, latestMovies);
            request.setAttribute(AttributeName.LATEST_USERS_LIST, latestUsers);
            request.setAttribute(AttributeName.LATEST_REVIEWED_MOVIES_LIST, latestReviewedMovies);
            router.setPagePath(PagePath.ADMIN_CABINET_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
