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
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.DIRECTOR_ID;

import static edu.epam.project.controller.command.AttributeName.DIRECTOR;

public class ToEditDirectorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToEditDirectorCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long directorId = Long.parseLong(request.getParameter(DIRECTOR_ID));
        try {
            Optional<Director> directorInfo = movieService.findDirectorInfoByDirectorId(directorId);
            if (directorInfo.isPresent()) {
                Director director = directorInfo.get();
                request.setAttribute(DIRECTOR, director);
                router.setPagePath(PagePath.EDIT_DIRECTOR_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
