package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
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
import static edu.epam.project.controller.command.AttributeName.POSITIVE_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.MIXED_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.NEGATIVE_REVIEWS;
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
        String userName = (String) session.getAttribute(USER_NAME);
        try {
            int amountOfComments = commentService.countUserCommentsByUserName(userName);
            int positiveReviews = ratingService.countPositiveMovieRatingByUserName(userName);
            int mixedReviews = ratingService.countMixedMovieRatingByUserName(userName);
            int negativeReviews = ratingService.countNegativeMovieRatingByUserName(userName);
            int averageMovieRating = ratingService.countAverageMovieRatingOfUser(userName);
            int amountOfReviews = ratingService.countAmountOfUserScoresByUserName(userName);
            Optional<Rating> latestHighScore = ratingService.findLatestHighScoreByUserName(userName);
            Optional<Rating> latestLowScore = ratingService.findLatestLowScoreByUserName(userName);
            List<Movie> ratedMovies = movieService.findRatedMoviesByUserName(userName);
            if (amountOfComments >= 0 && positiveReviews >= 0
                    && mixedReviews >= 0 && negativeReviews >= 0
                    && averageMovieRating >= 0 && amountOfReviews >= 0) {
                router.setPagePath(PagePath.USER_PROFILE);
                request.setAttribute(COMMENT, amountOfComments);
                request.setAttribute(POSITIVE_REVIEWS, positiveReviews);
                request.setAttribute(MIXED_REVIEWS, mixedReviews);
                request.setAttribute(NEGATIVE_REVIEWS, negativeReviews);
                request.setAttribute(AVERAGE_MOVIE_RATING, averageMovieRating);
                request.setAttribute(RATED_MOVIES_LIST, ratedMovies);
                request.setAttribute(AMOUNT_OF_USER_REVIEWS, amountOfReviews);
            }
            if (latestHighScore.isPresent() && latestLowScore.isPresent()) {
                Rating highRating = latestHighScore.get();
                Rating lowRating = latestLowScore.get();
                int highScore = highRating.getScore();
                int lowScore = lowRating.getScore();
                long lowMovieId = lowRating.getMovieId();
                long highMovieId = highRating.getMovieId();
                String lowScoreTitle = lowRating.getMovieTitle();
                String highScoreTitle = highRating.getMovieTitle();
                request.setAttribute(LATEST_HIGH_SCORE, highScore);
                request.setAttribute(LATEST_LOW_SCORE, lowScore);
                request.setAttribute(LOW_SCORE_MOVIE_TITLE, lowScoreTitle);
                request.setAttribute(HIGH_SCORE_MOVIE_TITLE, highScoreTitle);
                request.setAttribute(LOW_MOVIE_ID, lowMovieId);
                request.setAttribute(HIGH_MOVIE_ID, highMovieId);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
