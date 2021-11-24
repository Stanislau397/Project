package edu.epam.project.controller.command.impl;

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

import static edu.epam.project.controller.command.AttributeName.NEWEST_MOVIES_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;
import static edu.epam.project.controller.command.AttributeName.PAGES;
import static edu.epam.project.controller.command.AttributeName.PAGE_NUMBER;

import static edu.epam.project.controller.command.RequestParameter.PAGE_PARAMETER;

public class NewestMoviesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(NewestMoviesCommand.class);
    private static final int TOTAL = 15;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        int page = getPageNumber(request);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        try {
            List<Movie> newestMovies = movieService.findNewestMovies(page, TOTAL);
            List<Genre> genres = movieService.findAllGenres();
            int newestMoviesSize = movieService.countNewestMovies();
            int amountOfPages = countPages(newestMoviesSize);
            request.setAttribute(NEWEST_MOVIES_LIST, newestMovies);
            request.setAttribute(GENRES_LIST, genres);
            request.setAttribute(PAGE_NUMBER, pageNumber);
            request.setAttribute(PAGES, amountOfPages);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    private int getPageNumber(HttpServletRequest request) {
        int pageId = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        return pageId == 1 ? 0 : (pageId - 1) * TOTAL + 1;
    }

    private int countPages(int newestMoviesSize) throws ServiceException {
        return (newestMoviesSize + TOTAL - 1) / TOTAL;
    }
}
