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
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.SCORE;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class RateMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RateMovieCommand.class);
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String page = request.getHeader(REFERER);
        int rating = Integer.parseInt(request.getParameter(SCORE));
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        String userName = (String) session.getAttribute(USER_NAME);
        try {
            if (ratingService.rateMovie(movieId, userName, rating)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
