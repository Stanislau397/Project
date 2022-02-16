package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.Rating;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.impl.RatingServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

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
        LocalDateTime createdAt = LocalDateTime.now();
        User user = User.newUserBuilder().withUserId(userId).build();
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        Rating rating = Rating.newRatingBuilder()
                .withUser(user)
                .withMovie(movie)
                .withScore(score)
                .withCreatedAt(createdAt)
                .build();
        try {
            if (!ratingService.ratingExistsByUserIdAndMovieId(userId, movieId)) {
                ratingService.add(rating);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(page);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
