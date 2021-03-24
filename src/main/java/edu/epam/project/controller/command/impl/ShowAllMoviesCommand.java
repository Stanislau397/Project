package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Genre;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static edu.epam.project.controller.command.AttributeName.MOVIE_LIST;
import static edu.epam.project.controller.command.AttributeName.MOVIE_YEARS_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;

public class ShowAllMoviesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try {
            List<Movie> movies = movieService.findAllMovies();
            List<Integer> years= movieService.findAllMovieYears();
            List<Genre> genres = movieService.findAllGenres();
            if (movies.size() > 0) {
                router.setPagePath(PagePath.MOVIE_PAGE);
                request.setAttribute(MOVIE_LIST, movies);
                request.setAttribute(MOVIE_YEARS_LIST, years);
                request.setAttribute(GENRES_LIST, genres);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
