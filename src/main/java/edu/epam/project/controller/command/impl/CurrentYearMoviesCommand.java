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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static edu.epam.project.controller.command.AttributeName.MOVIES_BY_CURRENT_YEAR_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;

public class CurrentYearMoviesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CurrentYearMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        try {
            List<Movie> currentYearMovies = movieService.findAllCurrentYearMovies();
            List<Genre> genres = movieService.findAllGenres();
            if (currentYearMovies.size() > 0) {
                request.setAttribute(GENRES_LIST, genres);
                request.setAttribute(MOVIES_BY_CURRENT_YEAR_LIST, currentYearMovies);
                router.setPagePath(PagePath.MOVIE_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
