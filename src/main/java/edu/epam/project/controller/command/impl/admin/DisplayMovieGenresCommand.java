package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Genre;
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

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.TITLE;

import static edu.epam.project.controller.command.AttributeName.MOVIE_GENRES_LIST;

public class DisplayMovieGenresCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayMovieGenresCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        String movieTitle = request.getParameter(TITLE);
        try {
            List<Genre> genres = movieService.findAllGenres();
            List<Genre> movieGenres = movieService.findGenresForMovieByMovieId(movieId);
            request.setAttribute(MOVIE_GENRES_LIST, movieGenres);
            request.setAttribute(AttributeName.MOVIE_TITLE, movieTitle);
            request.setAttribute(AttributeName.MOVIE_ID, movieId);
            request.setAttribute(AttributeName.GENRES_LIST, genres);
            router.setPagePath(PagePath.EDIT_MOVIE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
