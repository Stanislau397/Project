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
import java.util.List;

import static edu.epam.project.controller.command.RequestParameter.KEY_WORD_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.ACTORS_BY_KEY_WORDS_LIST;

public class SearchActorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SearchActorCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String keyWords = request.getParameter(KEY_WORD_PARAMETER);
        try {
            List<Actor> actorsByKeyWords = movieService.findActorsByKeyWords(keyWords);
            request.setAttribute(ACTORS_BY_KEY_WORDS_LIST, actorsByKeyWords);
            router.setPagePath(PagePath.ALL_ACTORS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
