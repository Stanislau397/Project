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

import static edu.epam.project.controller.command.RequestParameter.DIRECTOR_ID;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.ErrorMessage.DELETE_DIRECTOR_ERROR_MSG;

import static edu.epam.project.controller.command.SessionAttribute.ERROR;
import static edu.epam.project.controller.command.SessionAttribute.DIRECTOR_REMOVED;

public class DeleteDirectorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteDirectorCommand.class);
    private static final String DIRECTOR_REMOVED_MSG = "director has been successfully removed";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long directorId = Long.parseLong(request.getParameter(DIRECTOR_ID));
        try {
            if (movieService.deleteDirectorById(directorId)) {
                session.setAttribute(DIRECTOR_REMOVED, DIRECTOR_REMOVED_MSG);
            } else {
                session.setAttribute(ERROR, DELETE_DIRECTOR_ERROR_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
