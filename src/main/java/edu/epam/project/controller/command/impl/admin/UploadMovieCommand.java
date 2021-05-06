package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
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
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.*;

public class UploadMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UploadMovieCommand.class);
    private static final String DIRECTORY_PATH = "C:/project/src/main/webapp/css/image";
    public static final String SEPARATOR = "/";
    public static final int NUMBER = 26;
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        Part part = request.getPart(FILE);
        String title = request.getParameter(TITLE);
        String description = request.getParameter(DESCRIPTION);
        int runTime = Integer.parseInt(request.getParameter(RUN_TIME));
        String country = request.getParameter(COUNTRY);
        Date releaseDate = Date.valueOf(request.getParameter(RELEASE_DATE));
        String id = request.getParameter(GENRE_ID);
        long genreId = Long.parseLong(id);
        String fileName = part.getSubmittedFileName();
        String savePath = DIRECTORY_PATH + SEPARATOR + fileName;
        String picturePath = savePath.substring(NUMBER);
        try {
            File file = new File(savePath);
            part.write(savePath + File.separator);
            router.setPagePath(PagePath.MOVIE_PAGE);
            Movie movie = new Movie(title, runTime, country, description, releaseDate, picturePath);
            movieService.add(movie);
            Optional<Movie> movieOptional = movieService.findMovieByTitle(title);
            if (movieOptional.isPresent()) {
                movie = movieOptional.get();
            }
            long movieId = movie.getMovieId();
            movieService.addGenreToMovie(genreId, movieId);
            router.setPagePath(PagePath.MOVIE_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
