package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
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
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.ACTOR_ID;
import static edu.epam.project.controller.command.AttributeName.ACTOR;

public class ToEditActorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToEditActorCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long actorId = Long.parseLong(request.getParameter(ACTOR_ID));
        try {
            Optional<Actor> actorInfo = movieService.findActorById(actorId);
            if (actorInfo.isPresent()) {
                Actor actor = actorInfo.get();
                request.setAttribute(ACTOR, actor);
                router.setPagePath(PagePath.EDIT_ACTOR_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
