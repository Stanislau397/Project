package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Director;
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
import java.util.Set;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;
import static edu.epam.project.controller.command.RequestParameter.BIRTH_DATE;
import static edu.epam.project.controller.command.RequestParameter.FILE;

import static edu.epam.project.controller.command.SessionAttribute.DIRECTOR;

public class AddDirectorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddDirectorCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 9.0/webapps/image/director/";
    private static final String IMAGE_PATH = "localhost:8080/image/director";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Part part = request.getPart(FILE);
        Set<String> parameterNames = request.getParameterMap().keySet();
        String currentPage = request.getHeader(REFERER);
        Director director = new Director();
        try {
            for (String fieldName : parameterNames) {
                processFormFields(director, fieldName, request);
            }
            processUploadedFile(director, part);
            if (movieService.addDirector(director)) {
                session.setAttribute(DIRECTOR, director);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }

    private void processFormFields(Director director, String fieldName, HttpServletRequest request) {
        switch (fieldName) {
            case FIRST_NAME:
                String firstName = request.getParameter(FIRST_NAME);
                director.setFirstName(firstName);
                break;
            case LAST_NAME:
                String lastName = request.getParameter(LAST_NAME);
                director.setLastName(lastName);
                break;
            case BIRTH_DATE:
                String birth_date = request.getParameter(BIRTH_DATE);
                director.setBirthDate(birth_date);
                break;
        }
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

    private void processUploadedFile(Director director, Part part) throws IOException {
        String savePath = getSavePath(part);
        String picturePath = getPicturePath(part);
        director.setPicture(picturePath);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
