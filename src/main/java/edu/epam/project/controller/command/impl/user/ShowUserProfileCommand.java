package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.RatingService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.service.impl.RatingServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

import static edu.epam.project.controller.command.AttributeName.USER_NAME;
import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.PAGE_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.RATED_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.POSITIVE_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.MIXED_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.NEGATIVE_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.ALL_REVIEWS;
import static edu.epam.project.controller.command.AttributeName.AVERAGE_MOVIE_RATING;
import static edu.epam.project.controller.command.AttributeName.LATEST_HIGH_SCORE_MOVIE;
import static edu.epam.project.controller.command.AttributeName.LATEST_LOW_SCORE_MOVIE;
import static edu.epam.project.controller.command.AttributeName.PAGES;
import static edu.epam.project.controller.command.AttributeName.PAGE_ID;
import static edu.epam.project.controller.command.AttributeName.COUNT_COMMENTS;

public class ShowUserProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowUserProfileCommand.class);
    private static final int TOTAL = 5;
    private MovieService movieService = new MovieServiceImpl();
    private UserService userService = new UserServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userName = (String) session.getAttribute(USER_NAME);
        int pageId = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        int start = getStartPoint(request, pageId);
        if (request.getParameter(USER_NAME_PARAMETER) != null) {
            userName = request.getParameter(USER_NAME_PARAMETER);
        }
        try {
            Optional<User> userInfo = userService.findUserByUserName(userName);
            List<Movie> ratedMovies = movieService.findRatedMoviesByUserName(userName, start, TOTAL);
            int countPositiveReviews = ratingService.countPositiveMovieScores(userName);
            int countMixedReviews = ratingService.countMixedMovieScores(userName);
            int countNegativeReviews = ratingService.countNegativeMovieScores(userName);
            int countAllReviews = ratingService.countAllMovieScores(userName);
            int countAverageMovieRating = ratingService.countAverageMovieRatingOfUser(userName);
            int countUserComments = commentService.countUserCommentsByUserName(userName);
            int pages = countPages(userName);
            Optional<Movie> latestHighScoreMovie = movieService.findLatestHighRatedMovieForUser(userName);
            Optional<Movie> latestLowScoreMovie = movieService.findLatestLowRatedMovieForUser(userName);
            if (latestHighScoreMovie.isPresent()) {
                Movie highScoreMovie = latestHighScoreMovie.get();
                request.setAttribute(LATEST_HIGH_SCORE_MOVIE, highScoreMovie);
            }
            if (latestLowScoreMovie.isPresent()) {
                Movie lowScoreMovie = latestLowScoreMovie.get();
                request.setAttribute(LATEST_LOW_SCORE_MOVIE, lowScoreMovie);
            }
            if (userInfo.isPresent()) {
                User user = userInfo.get();
                router.setPagePath(PagePath.USER_PROFILE);
                request.setAttribute(AttributeName.USER, user);
                request.setAttribute(RATED_MOVIES_LIST, ratedMovies);
                request.setAttribute(POSITIVE_REVIEWS, countPositiveReviews);
                request.setAttribute(MIXED_REVIEWS, countMixedReviews);
                request.setAttribute(NEGATIVE_REVIEWS, countNegativeReviews);
                request.setAttribute(ALL_REVIEWS, countAllReviews);
                request.setAttribute(AVERAGE_MOVIE_RATING, countAverageMovieRating);
                request.setAttribute(PAGES, pages);
                request.setAttribute(COUNT_COMMENTS, countUserComments);
                request.setAttribute(PAGE_ID, pageId);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }

    private int getStartPoint(HttpServletRequest request, int pageId) {
        return pageId == 1 ? 0 : (pageId - 1) * TOTAL + 1;
    }

    private int countPages(String userName) throws ServiceException {
        int ratedMoviesSize = movieService.countUserRatedMovies(userName);
        return (ratedMoviesSize + TOTAL - 1) / TOTAL;
    }
}
