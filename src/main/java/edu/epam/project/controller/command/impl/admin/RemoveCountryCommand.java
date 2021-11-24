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

import static edu.epam.project.controller.command.RequestParameter.COUNTRY_ID;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.ErrorMessage.DELETE_COUNTRY_ERROR_MSG;

import static edu.epam.project.controller.command.SessionAttribute.COUNTRY_REMOVED;
import static edu.epam.project.controller.command.SessionAttribute.ERROR;

public class RemoveCountryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveCountryCommand.class);
    private static final String REMOVE_COUNTRY_MSG = "Country has been removed";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long countryId = Long.parseLong(request.getParameter(COUNTRY_ID));
        try {
            if (movieService.removeCountryById(countryId)) {
                session.setAttribute(COUNTRY_REMOVED, REMOVE_COUNTRY_MSG);
            } else {
                session.setAttribute(ERROR, DELETE_COUNTRY_ERROR_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
