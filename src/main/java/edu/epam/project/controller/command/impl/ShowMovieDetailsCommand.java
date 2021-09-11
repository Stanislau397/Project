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
import java.util.List;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.AttributeName.MOVIE_INFO;
import static edu.epam.project.controller.command.AttributeName.COMMENTS_LIST;
import static edu.epam.project.controller.command.AttributeName.ACTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.DIRECTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.USER_SCORE;
import static edu.epam.project.controller.command.AttributeName.RATED_MOVIE;
import static edu.epam.project.controller.command.AttributeName.MOVIE_GENRES_LIST;

public class ShowMovieDetailsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String id = request.getParameter(MOVIE_ID);
        int movieId = Integer.parseInt(id);
        String userName = (String) session.getAttribute(SessionAttribute.USER_NAME);
        Movie movie;
        try {
            List<Comment> comments = commentService.findCommentsByMovieId(movieId);
            List<Actor> actors = movieService.findActorsByMovieId(movieId);
            List<Director> directors = movieService.findDirectorsByMovieId(movieId);
            List<Genre> movieGenres = movieService.findMovieGenresByMovieId(movieId);
            Optional<Movie> optionalMovie = movieService.findMovieById(movieId);
            int userScore = ratingService.findMovieScoreByUserNameAndMovieId(userName, movieId);
            boolean isRated = ratingService.isUserAlreadyVoted(userName, movieId);
            if (isRated) {
                request.setAttribute(USER_SCORE, userScore);
            } else {
                request.setAttribute(RATED_MOVIE, isRated);
            }
            if (optionalMovie.isPresent()) {
                movie = optionalMovie.get();
                request.setAttribute(MOVIE_INFO, movie);
            }
            request.setAttribute(ACTORS_LIST, actors);
            request.setAttribute(DIRECTORS_LIST, directors);
            request.setAttribute(COMMENTS_LIST, comments);
            request.setAttribute(MOVIE_GENRES_LIST, movieGenres);
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
