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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.RATING_ID;
import static edu.epam.project.controller.command.RequestParameter.USER_ID;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

public class RemoveRatingCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveRatingCommand.class);
    private RatingService ratingService = new RatingServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        long ratingId = Long.parseLong(request.getParameter(RATING_ID));
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            if (ratingService.ratingExistsByUserIdAndMovieId(userId, movieId)) {
                ratingService.deleteById(ratingId);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
