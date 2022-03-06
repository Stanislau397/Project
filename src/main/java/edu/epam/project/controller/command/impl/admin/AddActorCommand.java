package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.entity.Actor;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.FileService;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.FileServiceImpl;
import edu.epam.project.service.impl.MovieServiceImpl;
import edu.epam.project.util.FileUploader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;

import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;
import static edu.epam.project.controller.command.RequestParameter.DAY_OF_MONTH;
import static edu.epam.project.controller.command.RequestParameter.MONTH;
import static edu.epam.project.controller.command.RequestParameter.YEAR;

public class AddActorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddActorCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/actor/";
    private static final String IMAGE_PATH_FOR_DB = "http://localhost:8080/image/actor/";
    private static final String DEFAULT_IMAGE = "/image/istockphoto-1016744004-612x612.jpg";
    private MovieService movieService = new MovieServiceImpl();
    private FileService fileService = new FileServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        Part part = request.getPart(RequestParameter.FILE);
        String currentPage = request.getHeader(RequestParameter.REFERER);

        String firstname = request.getParameter(FIRST_NAME);
        String lastname = request.getParameter(LAST_NAME);
        int dayOfMonth = Integer.parseInt(request.getParameter(DAY_OF_MONTH));
        int month = Integer.parseInt(request.getParameter(MONTH));
        int year = Integer.parseInt(request.getParameter(YEAR));

        Actor actor = Actor.newActorBuilder()
                .withFirstname(firstname)
                .withLastname(lastname)
                .withBirthDate(LocalDate.of(year, month, dayOfMonth))
                .build();
        try {
            if (movieService.addActor(actor)) {
                FileUploader.save(part, DIRECTORY_PATH, "");
                session.setAttribute(SessionAttribute.ACTOR, actor);
            } else {
                session.setAttribute(SessionAttribute.ACTOR_ALREADY_EXISTS, actor);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
