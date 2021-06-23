package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
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

import static edu.epam.project.controller.command.RequestParameter.KEY_WORD_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.DIRECTORS_BY_KEY_WORDS_LIST;

public class SearchDirectorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SearchDirectorCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String keyWords = request.getParameter(KEY_WORD_PARAMETER);
        try {
            List<Director> directorsByKeyWords = movieService.findDirectorsByKeyWords(keyWords);
            request.setAttribute(DIRECTORS_BY_KEY_WORDS_LIST, directorsByKeyWords);
            router.setPagePath(PagePath.ALL_DIRECTORS);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
