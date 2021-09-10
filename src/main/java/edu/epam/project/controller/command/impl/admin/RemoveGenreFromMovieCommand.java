package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
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

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.GENRE_ID;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.DELETE_GENRE_ERROR;
import static edu.epam.project.controller.command.SessionAttribute.GENRE_REMOVED;

import static edu.epam.project.controller.command.ErrorMessage.DELETE_GENRE_ERROR_MSG;

public class RemoveGenreFromMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveGenreFromMovieCommand.class);
    private static final String GENRE_REMOVED_MSG = "Genre has been removed from this movie";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        long genreId = Long.parseLong(request.getParameter(GENRE_ID));
        try {
            if (movieService.removeGenreFromMovieByMovieAndGenreId(genreId, movieId)) {
                session.setAttribute(GENRE_REMOVED, GENRE_REMOVED_MSG);
            } else {
                session.setAttribute(DELETE_GENRE_ERROR, DELETE_GENRE_ERROR_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
            request.setAttribute(AttributeName.MOVIE_ID, movieId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
