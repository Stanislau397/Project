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

import static edu.epam.project.controller.command.AttributeName.*;
import static edu.epam.project.controller.command.RequestParameter.GENRE_TITLE_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_YEAR;

public class MoviesByYearAndGenre implements Command {

    private static final Logger logger = LogManager.getLogger(MoviesByYearCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        int movieYear = Integer.parseInt(request.getParameter(MOVIE_YEAR));
        String genreTitle = request.getParameter(GENRE_TITLE_PARAMETER);
        Genre genre = new Genre();
        genre.setTitle(genreTitle);
        try {
            List<Movie> moviesByGenreAndYear = movieService.findMoviesByGenreAndYear(genre, movieYear);
            List<Integer> years= movieService.findAllMovieYears();
            List<Genre> genres = movieService.findAllGenres();
            request.setAttribute(MOVIES_BY_GENRE_AND_YEAR_LIST, moviesByGenreAndYear);
            request.setAttribute(MOVIE_YEARS_LIST, years);
            request.setAttribute(GENRES_LIST, genres);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
