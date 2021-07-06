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
import java.io.IOException;
import java.sql.Date;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;
import static edu.epam.project.controller.command.RequestParameter.BIRTH_DATE;
import static edu.epam.project.controller.command.RequestParameter.HEIGHT;
import static edu.epam.project.controller.command.RequestParameter.DIRECTOR_ID;

public class EditDirectorInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditDirectorInfoCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        String firstName = request.getParameter(FIRST_NAME);
        String lastName = request.getParameter(LAST_NAME);
        Date birth_date = Date.valueOf(request.getParameter(BIRTH_DATE));
        double height = Double.parseDouble(request.getParameter(HEIGHT));
        long directorId = Long.parseLong(request.getParameter(DIRECTOR_ID));
        try {
            if (movieService.updateDirectorInfoByDirectorId(directorId, firstName, lastName, birth_date, height)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
