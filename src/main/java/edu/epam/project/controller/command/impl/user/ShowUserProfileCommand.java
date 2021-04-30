package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.Rating;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.RatingServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

import static edu.epam.project.controller.command.RequestParameter.COMMENT;
import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.LATEST_HIGH_SCORE;
import static edu.epam.project.controller.command.AttributeName.LATEST_LOW_SCORE;
import static edu.epam.project.controller.command.AttributeName.AVERAGE_MOVIE_RATING;
import static edu.epam.project.controller.command.AttributeName.HIGH_SCORE_MOVIE_TITLE;
import static edu.epam.project.controller.command.AttributeName.LOW_SCORE_MOVIE_TITLE;
import static edu.epam.project.controller.command.AttributeName.LOW_MOVIE_ID;
import static edu.epam.project.controller.command.AttributeName.HIGH_MOVIE_ID;
import static edu.epam.project.controller.command.AttributeName.RATED_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.AMOUNT_OF_USER_REVIEWS;

public class ShowUserProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowUserProfileCommand.class);
    private CommentService commentService = new CommentServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userName;
        if (request.getParameter(USER_NAME_PARAMETER) != null) {
            userName = request.getParameter(USER_NAME_PARAMETER);
        } else {
            userName = (String) session.getAttribute(USER_NAME);
        }
        try {
            List<Movie> ratedMovies = movieService.findRatedMoviesByUserName(userName);
            router.setPagePath(PagePath.USER_PROFILE);
            request.setAttribute(AttributeName.USER_NAME, userName);
            request.setAttribute(RATED_MOVIES_LIST, ratedMovies);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
