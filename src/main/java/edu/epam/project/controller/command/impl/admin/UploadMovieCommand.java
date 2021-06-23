package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.parser.ActorParser;
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
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static edu.epam.project.controller.command.RequestParameter.*;

public class UploadMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UploadMovieCommand.class);
    private static final String DIRECTORY_PATH = "C:/project/src/main/webapp/css/image";
    private static final String SEPARATOR = "/";
    private ActorParser actorParser = new ActorParser();
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        Part part = request.getPart(FILE);
        Set<String> parameterNames = request.getParameterMap().keySet();
        String currentPage = request.getHeader(REFERER);
        Movie movie = new Movie();
        try {
            for (String fieldName : parameterNames) {
                processMovieFormFields(movie, part, fieldName, request);
            }
            processUploadedFile(movie, part);
            if (movieService.add(movie)) {
                String movieTitle = movie.getTitle();
                Optional<Movie> movieOptional = movieService.findMovieByTitle(movieTitle);
                if (movieOptional.isPresent()) {
                    movie = movieOptional.get();
                    long movieId = movie.getMovieId();
                    processActorFormField(request, movie);
                    processDirectorFormField(request, movie);
                    processGenreFormField(request, movieId);
                }
                router.setPagePath(PagePath.MOVIE_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }

    private String getSavePath(Part part) {
        String fileName = part.getSubmittedFileName();
        return (DIRECTORY_PATH + SEPARATOR + fileName);
    }

    private String getPicturePath(Part part) {
        String savePath = getSavePath(part);
        return savePath.substring(savePath.indexOf("/css"));
    }

    private void processGenreFormField(HttpServletRequest request, long movieId) throws ServiceException {
        String[] genres = request.getParameterValues(GENRES);
        System.out.println(Arrays.toString(genres));
        for (String genre : genres) {
            long genreId = Long.parseLong(genre);
            movieService.addGenreToMovie(genreId, movieId);
        }
    }

    private void processActorFormField(HttpServletRequest request, Movie movie) throws ServiceException {
        long movieId = movie.getMovieId();
        String[] actors = request.getParameterValues(ACTORS);
        for (String actor : actors) {
            long actorId = Long.parseLong(actor);
            movieService.addActorToMovieById(actorId, movieId);
        }
    }

    private void processDirectorFormField(HttpServletRequest request, Movie movie) throws ServiceException {
        long movieId = movie.getMovieId();
        String[] directors = request.getParameterValues(DIRECTOR);
        for (String director : directors) {
            long directorId = Long.parseLong(director);
            movieService.addDirectorToMovieById(directorId, movieId);
        }
    }

    private void processUploadedFile(Movie movie, Part part) throws IOException {
        String savePath = getSavePath(part);
        String picturePath = getPicturePath(part);
        movie.setPicture(picturePath);
        File file = new File(savePath);
        part.write(file + File.separator);
    }

    private void processMovieFormFields(Movie movie, Part part, String fieldName, HttpServletRequest request) throws ServletException, IOException {
        switch (fieldName) {
            case TITLE:
                String title = request.getParameter(TITLE);
                movie.setTitle(title);
                break;
            case COUNTRY:
                String country = request.getParameter(COUNTRY);
                movie.setCountry(country);
                break;
            case RUN_TIME:
                int runTime = Integer.parseInt(request.getParameter(RUN_TIME));
                movie.setRunTime(runTime);
            case DESCRIPTION:
                String description = request.getParameter(DESCRIPTION);
                movie.setDescription(description);
                break;
            case RELEASE_DATE:
                Date releaseDate = Date.valueOf(request.getParameter(RELEASE_DATE));
                movie.setReleaseDate(releaseDate);
                break;
        }
    }
}
