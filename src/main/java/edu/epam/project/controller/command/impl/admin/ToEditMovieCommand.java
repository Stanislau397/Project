package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.entity.Actor;
import edu.epam.project.entity.Director;
import edu.epam.project.entity.Genre;
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
import java.util.Optional;

import static edu.epam.project.controller.command.AttributeName.GENRES_LIST;

public class ToEditMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToEditMovieCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long movieId = Long.parseLong(request.getParameter(RequestParameter.MOVIE_ID));
        Movie movie;
        try {
            List<Genre> genres = movieService.findAllGenres();
            List<Actor> actors = movieService.findActorsByMovieId(movieId);
            List<Director> directors = movieService.findDirectorsByMovieId(movieId);
            Optional<Movie> optionalMovie = movieService.findMovieById(movieId);
            if (optionalMovie.isPresent()) {
                movie = optionalMovie.get();
                request.setAttribute(AttributeName.MOVIE_INFO, movie);
                request.setAttribute(AttributeName.ACTORS_LIST, actors);
                request.setAttribute(AttributeName.DIRECTORS_LIST, directors);
                request.setAttribute(GENRES_LIST, genres);
                router.setPagePath(PagePath.EDIT_MOVIE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
