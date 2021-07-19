package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
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

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static edu.epam.project.controller.command.AttributeName.USER_NAME;
import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.RATED_MOVIES_LIST;

public class ShowUserProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowUserProfileCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userName = (String) session.getAttribute(USER_NAME);
        if (request.getParameter(USER_NAME_PARAMETER) != null) {
            userName = request.getParameter(USER_NAME_PARAMETER);
        }
        try {
            Optional<User> userInfo = userService.findUserByUserName(userName);
            List<Movie> ratedMovies = movieService.findRatedMoviesByUserName(userName);
            if (userInfo.isPresent()) {
                User user = userInfo.get();
                router.setPagePath(PagePath.USER_PROFILE);
                request.setAttribute(AttributeName.USER, user);
                request.setAttribute(RATED_MOVIES_LIST, ratedMovies);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
