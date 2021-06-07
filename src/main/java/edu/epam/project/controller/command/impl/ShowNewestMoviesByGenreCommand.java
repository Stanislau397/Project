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

import static edu.epam.project.controller.command.RequestParameter.GENRE_TITLE_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.NEWEST_MOVIES_BY_GENRE_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;

public class ShowNewestMoviesByGenreCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowNewestMoviesByGenreCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String genreTitle = request.getParameter(GENRE_TITLE_PARAMETER);
        try {
            List<Movie> newestMoviesByGenre = movieService.findNewestMoviesByGenreTitle(genreTitle);
            List<Genre> genres = movieService.findAllGenres();
            request.setAttribute(GENRES_LIST, genres);
            request.setAttribute(NEWEST_MOVIES_BY_GENRE_LIST, newestMoviesByGenre);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
