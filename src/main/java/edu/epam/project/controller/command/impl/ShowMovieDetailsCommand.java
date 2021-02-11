package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.entity.*;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.*;
import edu.epam.project.sevice.impl.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class ShowMovieDetailsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();
    private RatingService ratingService = new RatingServiceImpl();
    private GenreService genreService = new GenreServiceImpl();
    private ActorService actorService = new ActorServiceImpl();
    private DirectorService directorService = new DirectorServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String id = request.getParameter(RequestParameter.MOVIE_ID);
        int movieId = Integer.parseInt(id);
        Movie movie;
        Genre genre;
        try {
            List<Comment> comments = movieService.findCommentsByMovieId(movieId);
            List<Actor> actors = actorService.findActorsByMovieId(movieId);
            List<Director> directors = directorService.findDirectorsByMovieId(movieId);
            Optional<Genre> optionalGenre = genreService.findMovieGenreByMovieId(movieId);
            Optional<Movie> optionalMovie = movieService.findMovieById(movieId);
            int movieRating = ratingService.countAverageMovieRating(movieId);
            if (optionalMovie.isPresent() && movieRating >= 0
                    && optionalGenre.isPresent()) {
                genre = optionalGenre.get();
                movie = optionalMovie.get();
                String title = movie.getTitle();
                String description = movie.getDescription();
                String picture = movie.getPicture();
                String country = movie.getCountry();
                String movieGenre = genre.getTitle();
                int runTime = movie.getRunTime();
                if (comments.size() != 0) {
                    request.setAttribute(RequestParameter.COMMENTS_LIST, comments);
                }
                if (movieRating != 0) {
                    request.setAttribute(RequestParameter.MOVIE_RATING_PARAMETER, movieRating);
                }
                request.setAttribute(RequestParameter.MOVIE_ID, movieId);
                request.setAttribute(RequestParameter.TITLE_PARAMETER, title);
                request.setAttribute(RequestParameter.DESCRIPTION_PARAMETER, description);
                request.setAttribute(RequestParameter.PICTURE_PARAMETER, picture);
                request.setAttribute(RequestParameter.MOVIE_RUN_TIME, runTime);
                request.setAttribute(RequestParameter.MOVIE_COUNTRY_PARAMETER, country);
                request.setAttribute(RequestParameter.MOVIE_GENRE_PARAMETER, movieGenre);
                request.setAttribute(RequestParameter.ACTORS_PARAMETER, actors);
                request.setAttribute(RequestParameter.DIRECTORS_PARAMETER, directors);
                router.setPagePath(PagePath.MOVIE_DETAIL_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
