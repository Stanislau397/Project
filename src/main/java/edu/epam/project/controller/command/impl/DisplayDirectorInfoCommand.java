package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
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

import static edu.epam.project.controller.command.RequestParameter.DIRECTOR_ID;
import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;



public class DisplayDirectorInfoCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DisplayDirectorInfoCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long directorId = Long.parseLong(request.getParameter(DIRECTOR_ID));
        String directorFirstName = request.getParameter(FIRST_NAME);
        String directorLastName = request.getParameter(LAST_NAME);
        try {
            List<Movie> movieForDirector = movieService.findMoviesForDirector(directorId);
            request.setAttribute(AttributeName.FIRST_NAME, directorFirstName);
            request.setAttribute(AttributeName.LAST_NAME, directorLastName);
            request.setAttribute(AttributeName.MOVIE_FOR_DIRECTOR_LIST, movieForDirector);
            router.setPagePath(PagePath.DIRECTOR_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
