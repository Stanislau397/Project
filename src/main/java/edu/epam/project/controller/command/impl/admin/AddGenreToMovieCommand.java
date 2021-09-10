package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.*;

import static edu.epam.project.controller.command.SessionAttribute.GENRE_ALREADY_EXISTS;
import static edu.epam.project.controller.command.SessionAttribute.GENRE_SUCCESSFULLY_ADDED;

import static edu.epam.project.controller.command.ErrorMessage.ADD_GENRE_TO_MOVIE_ERROR_MSG;

public class AddGenreToMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddGenreToMovieCommand.class);
    private static final String GENRE_ADDED_MSG = "Genre has been added to movie";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long genreId = Long.parseLong(request.getParameter(GENRE_ID));
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            if (movieService.isGenreAlreadyExistsForMovie(movieId, genreId)) {
                session.setAttribute(GENRE_ALREADY_EXISTS, ADD_GENRE_TO_MOVIE_ERROR_MSG);
            } else {
                movieService.addGenreToMovie(genreId, movieId);
                session.setAttribute(GENRE_SUCCESSFULLY_ADDED, GENRE_ADDED_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
