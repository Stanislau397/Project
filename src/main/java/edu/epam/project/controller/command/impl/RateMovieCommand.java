package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.RatingServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.SCORE_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.USER_ID;

public class RateMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RateMovieCommand.class);
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String page = request.getHeader(REFERER);
        int score = Integer.parseInt(request.getParameter(SCORE_PARAMETER));
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            if (!ratingService.isUserRatedMovie(userId, movieId)) {
                ratingService.add(movieId, userId, score);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
