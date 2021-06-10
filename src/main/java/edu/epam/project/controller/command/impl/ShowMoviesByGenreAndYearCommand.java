package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
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

public class ShowMoviesByGenreAndYearCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowMoviesByGenreAndYearCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String genreTitle = request.getParameter(RequestParameter.GENRE_TITLE_PARAMETER);
        Integer year = Integer.parseInt(request.getParameter(RequestParameter.MOVIE_YEAR));
        Genre genre = new Genre();
        genre.setGenreTitle(genreTitle);
        try {
            List<Movie> moviesByGenreAndYear = movieService.findMoviesByGenreAndYear(genre, year);
            List<Genre> genres = movieService.findAllGenres();
            request.setAttribute(AttributeName.MOVIES_BY_GENRE_AND_YEAR_LIST, moviesByGenreAndYear);
            request.setAttribute(AttributeName.GENRES_LIST, genres);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
