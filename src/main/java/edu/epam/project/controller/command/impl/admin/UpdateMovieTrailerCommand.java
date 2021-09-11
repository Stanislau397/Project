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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.FILE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.CHANGED_TRAILER;
import static edu.epam.project.controller.command.SessionAttribute.TRAILER_ERROR;

import static edu.epam.project.controller.command.ErrorMessage.EDIT_TRAILER_ERROR_MSG;

public class UpdateMovieTrailerCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UpdateMovieTrailerCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/trailer/";
    private static final String TRAILER_PATH = "localhost:8080/trailer";
    private static final String EDIT_TRAILER_SUCCESS_MSG = "Trailer has been updated.";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        String trailerPath = getTrailerPath(part);
        try {
            if (movieService.updateMovieTrailerByMovieId(movieId, trailerPath)) {
                processUploadedFile(part);
                session.setAttribute(CHANGED_TRAILER, EDIT_TRAILER_SUCCESS_MSG);
            } else {
                session.setAttribute(TRAILER_ERROR, EDIT_TRAILER_ERROR_MSG);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    private String getSavePath(Part part) {
        String fileName = part.getSubmittedFileName();
        return (DIRECTORY_PATH + fileName);
    }

    private String getTrailerPath(Part part) {
        String savePath = getSavePath(part);
        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
        return TRAILER_PATH + pictureName;
    }

    private void processUploadedFile(Part part) throws IOException {
        String savePath = getSavePath(part);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
