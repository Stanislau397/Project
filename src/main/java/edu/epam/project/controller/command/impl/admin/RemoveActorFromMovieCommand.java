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

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.ACTOR_ID;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.ACTOR_REMOVED;

public class RemoveActorFromMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveActorFromMovieCommand.class);
    private static final String ACTOR_REMOVED_MSG = "Actor has been removed from this movie";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        long actorId = Long.parseLong(request.getParameter(ACTOR_ID));
        try {
            if (movieService.deleteActorFromMovieByActorIdAndMovieId(actorId, movieId)) {
                session.setAttribute(ACTOR_REMOVED, ACTOR_REMOVED_MSG);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
