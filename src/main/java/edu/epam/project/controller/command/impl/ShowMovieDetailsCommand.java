package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.SessionAttribute;
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
import static edu.epam.project.controller.command.AttributeName.RATED_MOVIE;

public class ShowMovieDetailsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String id = request.getParameter(MOVIE_ID);
        int movieId = Integer.parseInt(id);
        String userName = (String) session.getAttribute(SessionAttribute.USER_NAME);
        Movie movie;
        try {
            movie = movieService.findMovieById(movieId);
            int userScore = ratingService.findMovieScoreByUserNameAndMovieId(userName, movieId);
            boolean isRated = ratingService.isUserAlreadyVoted(userName, movieId);
            if (isRated) {
                request.setAttribute(USER_SCORE, userScore);
            } else {
                request.setAttribute(RATED_MOVIE, isRated);
            }
            request.setAttribute(MOVIE_INFO, movie);
            request.setAttribute("user_score", userScore);
            router.setPagePath(PagePath.MOVIE_DETAIL_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
