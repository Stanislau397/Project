package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Genre;
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

import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;

public class ToUploadMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToUploadMovieCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws IOException, ServletException {
        Router router = new Router();
        try {
            List<Genre> genres = movieService.findAllGenres();
            request.setAttribute(GENRES_LIST, genres);
            router.setPagePath(PagePath.UPLOAD_MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.ADMIN_CABINET_PAGE);
        }
        return router;
    }
}
