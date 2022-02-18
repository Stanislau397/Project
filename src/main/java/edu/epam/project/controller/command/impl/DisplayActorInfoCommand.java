package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Actor;
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
import java.util.Optional;

import static edu.epam.project.controller.command.AttributeName.ACTOR;
import static edu.epam.project.controller.command.RequestParameter.ACTOR_ID;
import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;

import static edu.epam.project.controller.command.AttributeName.MOVIES_FOR_ACTOR_LIST;
import static edu.epam.project.controller.command.AttributeName.BEST_MOVIES_FOR_ACTOR_LIST;

public class DisplayActorInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayActorInfoCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long actorId = Long.parseLong(request.getParameter(ACTOR_ID));
        String actorFirstName = request.getParameter(FIRST_NAME);
        String actorLastName = request.getParameter(LAST_NAME);
        try {
            List<Movie> moviesForActor = movieService.findMoviesForActorByActorId(actorId);
            List<Movie> bestMoviesForActor = movieService.findBestMoviesForActorByActorId(actorId);
            Actor actor = movieService.findActorById(actorId);
            request.setAttribute(ACTOR, actor);
            request.setAttribute(MOVIES_FOR_ACTOR_LIST, moviesForActor);
            request.setAttribute(BEST_MOVIES_FOR_ACTOR_LIST, bestMoviesForActor);
            request.setAttribute(AttributeName.FIRST_NAME, actorFirstName);
            request.setAttribute(AttributeName.LAST_NAME, actorLastName);
            router.setPagePath(PagePath.ACTOR_PAGE);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
