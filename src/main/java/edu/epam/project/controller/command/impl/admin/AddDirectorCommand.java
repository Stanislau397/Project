package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Director;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.FileService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.FileServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;

import static edu.epam.project.controller.command.RequestParameter.*;
import static edu.epam.project.controller.command.SessionAttribute.DIRECTOR;

public class AddDirectorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddDirectorCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/director/";
    private static final String IMAGE_PATH = "http://localhost:8080/image/director/";
    private MovieService movieService = new MovieServiceImpl();
    private FileService fileService = new FileServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        Part part = request.getPart(FILE);
        String firstname = request.getParameter(FIRST_NAME);
        String lastname = request.getParameter(LAST_NAME);
        String picture = fileService.getFilePathForDataBase(part, IMAGE_PATH);
        int dayOfMonth = Integer.parseInt(request.getParameter(DAY_OF_MONTH));
        int month = Integer.parseInt(request.getParameter(MONTH));
        int year = Integer.parseInt(request.getParameter(YEAR));
        LocalDate birthDate = LocalDate.of(year, month, dayOfMonth);
        Director director = Director.newDirectorBuilder()
                .withPicture(picture)
                .withFirstname(firstname)
                .withLastname(lastname)
                .withBirthDate(birthDate)
                .build();
        try {
            if (movieService.addDirector(director)) {
                fileService.save(part, DIRECTORY_PATH);
                session.setAttribute(DIRECTOR, director);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
