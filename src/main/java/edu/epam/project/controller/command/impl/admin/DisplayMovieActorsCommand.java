package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Actor;
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

import static edu.epam.project.controller.command.RequestParameter.TITLE;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.AttributeName.ACTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.MOVIE_TITLE;
import static edu.epam.project.controller.command.AttributeName.ALL_ACTORS_LIST;

public class DisplayMovieActorsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayMovieActorsCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String movieTitle = request.getParameter(TITLE);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            int totalActors = movieService.countActors();
            List<Actor> actors = movieService.findActorsByMovieId(movieId);
            List<Actor> allActors = movieService.findAllActors(0, totalActors);
            request.setAttribute(ACTORS_LIST, actors);
            request.setAttribute(MOVIE_TITLE, movieTitle);
            request.setAttribute(ALL_ACTORS_LIST, allActors);
            request.setAttribute(AttributeName.MOVIE_ID, movieId);
            router.setPagePath(PagePath.EDIT_MOVIE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
