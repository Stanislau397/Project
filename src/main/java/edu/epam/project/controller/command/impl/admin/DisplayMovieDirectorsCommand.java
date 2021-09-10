package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Director;
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

import static edu.epam.project.controller.command.AttributeName.DIRECTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.ALL_DIRECTORS_LIST;

public class DisplayMovieDirectorsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayMovieDirectorsCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        String movieTitle = request.getParameter(TITLE);
        try {
            int totalDirectors = movieService.countDirectors();
            List<Director> directors = movieService.findDirectorsByMovieId(movieId);
            List<Director> allDirectors = movieService.findAllDirectors(0, totalDirectors);
            request.setAttribute(DIRECTORS_LIST, directors);
            request.setAttribute(ALL_DIRECTORS_LIST, allDirectors);
            request.setAttribute(AttributeName.MOVIE_ID, movieId);
            request.setAttribute(AttributeName.MOVIE_TITLE, movieTitle);
            router.setPagePath(PagePath.EDIT_MOVIE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
