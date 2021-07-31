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

import static edu.epam.project.controller.command.RequestParameter.PAGE_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.MOVIE_LIST;
import static edu.epam.project.controller.command.AttributeName.MOVIE_YEARS_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;
import static edu.epam.project.controller.command.AttributeName.PAGES;

public class ShowAllMoviesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowAllMoviesCommand.class);
    private static final int TOTAL = 25;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        int page = getPageNumber(request);
        try {
            List<Movie> movies = movieService.findAllMovies(page, TOTAL);
            List<Integer> years= movieService.findAllMovieYears();
            List<Genre> genres = movieService.findAllGenres();
            int amountOfPages = countPages(movies);
            if (movies.size() > 0) {
                router.setPagePath(PagePath.MOVIE_PAGE);
                request.setAttribute(MOVIE_LIST, movies);
                request.setAttribute(MOVIE_YEARS_LIST, years);
                request.setAttribute(GENRES_LIST, genres);
                request.setAttribute(PAGES, amountOfPages);
                request.setAttribute("page1", Integer.parseInt(request.getParameter(PAGE_PARAMETER)));
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }

    private int getPageNumber(HttpServletRequest request) {
        int pageId = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        return pageId == 1 ? 0 : (pageId - 1) * TOTAL + 1;
    }

    private int countPages(List<Movie> movies) throws ServiceException {
        int moviesSize = movieService.countMovies();
        return (moviesSize + TOTAL - 1) / TOTAL;
    }
}
