package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.util.FileUploader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.FILE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.PICTURE_ERROR;
import static edu.epam.project.controller.command.SessionAttribute.CHANGED_PICTURE;
import static edu.epam.project.controller.command.ErrorMessage.EDIT_PICTURE_ERROR;

public class ChangeMoviePosterCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeMoviePosterCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/movie/";
    private static final String IMAGE_PATH = "http://localhost:8080/image/movie/";
    private static final String EDIT_PICTURE_SUCCESS_MSG = "Picture has been edited.";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            if (movieService.updateMoviePosterByMovieId(part, movieId)) {
                session.setAttribute(CHANGED_PICTURE, EDIT_PICTURE_SUCCESS_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
