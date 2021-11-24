package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
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

import static edu.epam.project.controller.command.AttributeName.NEWEST_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.MOST_RATED_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.UPCOMING_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.TRAILERS_LIST;

public class OpenHomePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(OpenHomePageCommand.class);
    private static final int TOTAL = 12;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        try {
            List<Movie> newestMovies = movieService.findNewestMovies(0, TOTAL);
            List<Movie> mostRatedMovies = movieService.findMostRatedMovies();
            List<Movie> upcomingMovies = movieService.findUpcomingMovies();
            List<Movie> trailers = movieService.findMoviesWithTrailer();
            request.setAttribute(NEWEST_MOVIES_LIST, newestMovies);
            request.setAttribute(MOST_RATED_MOVIES_LIST, mostRatedMovies);
            request.setAttribute(UPCOMING_MOVIES_LIST, upcomingMovies);
            request.setAttribute(TRAILERS_LIST, trailers);
            router.setPagePath(PagePath.HOME_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
