package edu.epam.project.controller.command.impl.admin;

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

public class FindAllMoviesCommand implements Command {

    private static final Logger logger = LogManager.getLogger(FindAllMoviesCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        try {
            int counter = movieService.countMovies();
            List<Movie> allMovies = movieService.findAllMovies(0, 25);
            if (allMovies.size() > 0) {
                router.setPagePath(PagePath.ALL_MOVIES_PAGE);
                request.setAttribute(AttributeName.MOVIE_LIST, allMovies);
                request.setAttribute(AttributeName.COUNTER, counter);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
