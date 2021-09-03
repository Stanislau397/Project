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

import static edu.epam.project.controller.command.RequestParameter.PAGE_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.PAGE_NUMBER;
import static edu.epam.project.controller.command.AttributeName.PAGES;
import static edu.epam.project.controller.command.AttributeName.DIRECTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.COUNTER;

public class DisplayAllDirectorsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayAllDirectorsCommand.class);
    private static final int TOTAL = 30;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        int page = getPageNumber(request);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_PARAMETER));
        try {
            List<Director> allDirectors = movieService.findAllDirectors(page, TOTAL);
            int amountOfPages = countPages(allDirectors);
            int counter = movieService.countDirectors();
            if (allDirectors.size() > 0) {
                request.setAttribute(DIRECTORS_LIST, allDirectors);
                request.setAttribute(COUNTER, counter);
                request.setAttribute(PAGES, amountOfPages);
                request.setAttribute(PAGE_NUMBER, pageNumber);
                router.setPagePath(PagePath.ALL_DIRECTORS);
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

    private int countPages(List<Director> directors) throws ServiceException {
        int directorsSize = movieService.countDirectors();
        return (directorsSize + TOTAL - 1) / TOTAL;
    }
}
