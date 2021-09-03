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

import static edu.epam.project.controller.command.RequestParameter.PAGE_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.PAGE_NUMBER;
import static edu.epam.project.controller.command.AttributeName.PAGES;
import static edu.epam.project.controller.command.AttributeName.ACTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.COUNTER;

public class DisplayAllActorsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayAllActorsCommand.class);
    private static final int TOTAL = 30;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        int page = getPageNumber(request);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        try {
            List<Actor> allActors = movieService.findAllActors(page, TOTAL);
            int amountOfPages = countPages(allActors);
            int counter = movieService.countActors();
            if (allActors.size() > 0) {
                request.setAttribute(COUNTER, counter);
                request.setAttribute(PAGES, amountOfPages);
                request.setAttribute(PAGE_NUMBER, pageNumber);
                request.setAttribute(ACTORS_LIST, allActors);
                router.setPagePath(PagePath.ALL_ACTORS);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    private int getPageNumber(HttpServletRequest request) {
        int pageId = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        return pageId == 1 ? 0 : (pageId - 1) * TOTAL + 1;
    }

    private int countPages(List<Actor> actors) throws ServiceException {
        int actorsSize = movieService.countActors();
        return (actorsSize + TOTAL - 1) / TOTAL;
    }
}
