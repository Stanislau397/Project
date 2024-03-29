package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Country;
import edu.epam.project.entity.Director;
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

import static edu.epam.project.controller.command.AttributeName.ACTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.DIRECTORS_LIST;
import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;
import static edu.epam.project.controller.command.AttributeName.COUNTRIES_LIST;

public class ToUploadMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToUploadMovieCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws IOException, ServletException {
        Router router = new Router();
        try {
            int totalActors = movieService.countActors();
            int totalDirectors = movieService.countDirectors();
            List<Genre> genres = movieService.findAllGenres();
            List<Country> allCountries = movieService.findAllCountries();
            List<Actor> allActors = movieService.findAllActors(0, totalActors);
            List<Director> allDirectors = movieService.findAllDirectors(0,totalDirectors);
            request.setAttribute(GENRES_LIST, genres);
            request.setAttribute(COUNTRIES_LIST, allCountries);
            request.setAttribute(ACTORS_LIST, allActors);
            request.setAttribute(DIRECTORS_LIST, allDirectors);
            router.setPagePath(PagePath.UPLOAD_MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.UPLOAD_MOVIE_PAGE);
        }
        return router;
    }
}
