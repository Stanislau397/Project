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

import static edu.epam.project.controller.command.AttributeName.MOVIE_YEARS_LIST;
import static edu.epam.project.controller.command.AttributeName.MOVIES_BY_GENRE_AND_YEAR_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRE_ATTRIBUTE;
import static edu.epam.project.controller.command.AttributeName.YEAR;
import static edu.epam.project.controller.command.RequestParameter.*;

public class ShowMoviesByGenreAndYearCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowMoviesByGenreAndYearCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String genreTitle = getGenreTitle(request);
        Integer year = getMovieYear(request);
        Genre genre = new Genre();
        genre.setGenreTitle(genreTitle);
        try {
            List<Movie> moviesByGenreAndYear = movieService.findMoviesByGenreAndYear(genre, year);
            List<Genre> genres = movieService.findAllGenres();
            List<Integer> years = movieService.findAllMovieYears();
            request.setAttribute(MOVIE_YEARS_LIST, years);
            request.setAttribute(MOVIES_BY_GENRE_AND_YEAR_LIST, moviesByGenreAndYear);
            request.setAttribute(GENRES_LIST, genres);
            request.setAttribute(GENRE_ATTRIBUTE, genreTitle);
            System.out.println(genreTitle);
            request.setAttribute(YEAR, year);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    private Integer getMovieYear(HttpServletRequest request) {
        Integer year = null;
        if (request.getParameter(MOVIE_YEAR) != null) {
            year = Integer.parseInt(request.getParameter(MOVIE_YEAR));
        }
        return year;
    }

    private String getGenreTitle(HttpServletRequest request) {
        String genreTitle = null;
        if (request.getParameter(GENRE_TITLE_PARAMETER) != null) {
            genreTitle = request.getParameter(GENRE_TITLE_PARAMETER);
        }
        return genreTitle;
    }
}
