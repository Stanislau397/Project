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

import static edu.epam.project.controller.command.RequestParameter.*;

public class UpdateMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UpdateMovieCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        String movieTitle = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        int runTime = Integer.parseInt(request.getParameter(RUN_TIME));
        Date releaseDate = Date.valueOf(request.getParameter(RELEASE_DATE));
        try {
            boolean update = movieService.updateTitleRunTimeReleaseDateDescriptionByMovieId(movieTitle, runTime, releaseDate, description, movieId);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
