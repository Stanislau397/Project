package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.*;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.*;
import edu.epam.project.service.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.AttributeName.MOVIE_INFO;
import static edu.epam.project.controller.command.AttributeName.USER_SCORE;

import static edu.epam.project.controller.command.SessionAttribute.USER_ATTR;

public class ShowMovieDetailsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        User user;
        long userId = 0;
        if (session.getAttribute(USER_ATTR) != null) {
            user = (User) session.getAttribute(USER_ATTR);
            userId = user.getUserId();
        }
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            Movie movie = movieService.findMovieById(movieId);
            Rating userScoreForMovie = ratingService.findPersonalUserScoreForMovie(userId, movieId);
            if (ratingService.isUserRatedMovie(userId, movieId)) {
                request.setAttribute(USER_SCORE, userScoreForMovie);
            }
            request.setAttribute(MOVIE_INFO, movie);
            router.setPagePath(PagePath.MOVIE_DETAIL_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
