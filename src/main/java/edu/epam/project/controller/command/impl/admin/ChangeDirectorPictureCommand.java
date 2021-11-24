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

import static edu.epam.project.controller.command.RequestParameter.DIRECTOR_ID;
import static edu.epam.project.controller.command.RequestParameter.FILE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.CHANGED_PICTURE;
import static edu.epam.project.controller.command.SessionAttribute.PICTURE_ERROR;
import static edu.epam.project.controller.command.ErrorMessage.EDIT_PICTURE_ERROR;

public class ChangeDirectorPictureCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeDirectorPictureCommand.class);
    private static final String DIRECTORY_PATH = "/usr/local/tomcat/webapps/storage/image/director/";
    private static final String IMAGE_PATH = "http://77.223.98.30/storage/image/director/";
    private static final String CHANGE_PICTURE_MSG = "Picture has been changed";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        String picturePath = getPicturePath(part);
        long directorId = Long.parseLong(request.getParameter(DIRECTOR_ID));
        try {
            if (movieService.updateDirectorPictureByDirectorId(directorId, picturePath)) {
                session.setAttribute(CHANGED_PICTURE, CHANGE_PICTURE_MSG);
                processUploadedFile(part);
            } else {
                session.setAttribute(PICTURE_ERROR, EDIT_PICTURE_ERROR);
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

    private String getPicturePath(Part part) {
        String savePath = getSavePath(part);
        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
        return IMAGE_PATH + pictureName;
    }

    private void processUploadedFile(Part part) throws IOException {
        String savePath = getSavePath(part);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
