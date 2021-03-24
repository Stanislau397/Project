package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
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

import static edu.epam.project.controller.command.RequestParameter.MOVIE_YEAR;
import static edu.epam.project.controller.command.AttributeName.MOVIES_BY_YEAR_LIST;
import static edu.epam.project.controller.command.AttributeName.MOVIE_YEARS_LIST;

public class MoviesByYearCommand implements Command {

    private static final Logger logger = LogManager.getLogger(MoviesByYearCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        int year = Integer.parseInt(request.getParameter(MOVIE_YEAR));
        try {
            List<Movie> moviesByYear = movieService.findMoviesByYear(year);
            List<Integer> years = movieService.findAllMovieYears();
            if (moviesByYear.size() > 0) {
                request.setAttribute(MOVIES_BY_YEAR_LIST, moviesByYear);
                request.setAttribute(MOVIE_YEARS_LIST, years);
                router.setPagePath(PagePath.MOVIE_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
