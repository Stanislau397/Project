package edu.epam.project.controller.command.impl.admin;

import java.io.File;
import java.io.IOException;
import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.sql.Date;
import java.util.Optional;
import java.util.Set;

import static edu.epam.project.controller.command.RequestParameter.*;

import static edu.epam.project.controller.command.SessionAttribute.MOVIE_TITLE_ATTR;
import static edu.epam.project.controller.command.SessionAttribute.MOVIE_ID_ATTR;
import static edu.epam.project.controller.command.SessionAttribute.INVALID_INPUT;

public class UploadMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UploadMovieCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/movie/";
    private static final String IMAGE_PATH = "http://localhost:8080/image/movie/";
    private static final String DEFAULT_MOVIE_IMAGE = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/default-movie-image.jpg";
    private static final String DEFAULT_DESCRIPTION = "The plot is currently unknown.";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        Set<String> parameterNames = request.getParameterMap().keySet();
        Movie movie = new Movie();
        for (String fieldName : parameterNames) {
            processMovieFormFields(movie, fieldName, request);
        }
        try {
            processUploadedFile(movie, part);
            if (movieService.add(movie)) {
                String movieTitle = movie.getTitle();
                Optional<Movie> movieOptional = movieService.findMovieByTitle(movieTitle);
                if (movieOptional.isPresent()) {
                    movie = movieOptional.get();
                    long movieId = movie.getMovieId();
                    processActorFormField(request, movieId);
                    processDirectorFormField(request, movieId);
                    processGenreFormField(request, movieId);
                    processCountryFormField(request, movieId);
                    session.setAttribute(MOVIE_TITLE_ATTR, movieTitle);
                    session.setAttribute(MOVIE_ID_ATTR, movieId);
                }
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            session.setAttribute(INVALID_INPUT, e.getMessage());
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }

    private String getSavePath(Part part) {
        String fileName = part.getSubmittedFileName();
        return (DIRECTORY_PATH + fileName);
    }

    private String getPicturePath(Part part) {
        String savePath = getSavePath(part);
        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
        return IMAGE_PATH + pictureName;
    }

    private void processGenreFormField(HttpServletRequest request, long movieId) throws ServiceException {
        String[] genres;
        if (request.getParameterValues(GENRES) != null) {
            genres = request.getParameterValues(GENRES);
            for (String genre : genres) {
                long genreId = Long.parseLong(genre);
                movieService.addGenreToMovie(genreId, movieId);
            }
        }
    }

    private void processActorFormField(HttpServletRequest request, long movieId) throws ServiceException {
        String[] actors;
        if (request.getParameterValues(ACTORS) != null) {
            actors = request.getParameterValues(ACTORS);
            for (String actor : actors) {
                long actorId = Long.parseLong(actor);
                movieService.addActorToMovieById(actorId, movieId);
            }
        }
    }

    private void processDirectorFormField(HttpServletRequest request, long movieId) throws ServiceException {
        String[] directors;
        if (request.getParameterValues(DIRECTOR) != null) {
            directors = request.getParameterValues(DIRECTOR);
            for (String director : directors) {
                long directorId = Long.parseLong(director);
                movieService.addDirectorToMovieById(directorId, movieId);
            }
        }
    }

    private void processCountryFormField(HttpServletRequest request, long movieId) throws ServiceException {
        String[] countries;
        if (request.getParameterValues(COUNTRIES) != null) {
            countries = request.getParameterValues(COUNTRIES);
            for (String country : countries) {
                long countryId = Long.parseLong(country);
                movieService.addCountryToMovieByMovieIdAndCountryId(movieId, countryId);
            }
        }
    }

    private void processUploadedFile(Movie movie, Part part) throws IOException {
        String savePath = getSavePath(part);
        String picturePath = getPicturePath(part);
        if (part.getSubmittedFileName().isEmpty()) {
            movie.setPicture(DEFAULT_MOVIE_IMAGE);
        } else {
            movie.setPicture(picturePath);
            File file = new File(savePath);
            part.write(file + File.separator);
        }
    }

    private void processMovieFormFields(Movie movie, String fieldName, HttpServletRequest request) throws ServletException, IOException {
        switch (fieldName) {
            case TITLE:
                String title = request.getParameter(TITLE);
                movie.setTitle(title);
                break;
            case RUN_TIME:
                int runTime;
                if (!request.getParameter(RUN_TIME).isEmpty()) {
                    runTime = Integer.parseInt(request.getParameter(RUN_TIME));
                    movie.setRunTime(runTime);
                } else {
                    movie.setRunTime(0);
                }
            case DESCRIPTION:
                String description;
                if (!request.getParameter(DESCRIPTION).isEmpty()) {
                    description = request.getParameter(DESCRIPTION);
                    movie.setDescription(description);
                } else {
                    movie.setDescription(DEFAULT_DESCRIPTION);
                }
                break;
            case RELEASE_DATE:
                Date releaseDate = Date.valueOf(request.getParameter(RELEASE_DATE));
                movie.setReleaseDate(releaseDate);
                break;
        }
    }
}
