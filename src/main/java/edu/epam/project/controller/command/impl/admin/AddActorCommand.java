package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Actor;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.FIRST_NAME;
import static edu.epam.project.controller.command.RequestParameter.LAST_NAME;
import static edu.epam.project.controller.command.RequestParameter.BIRTH_DATE;

public class AddActorCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddActorCommand.class);
    private static final String DIRECTORY_PATH = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/image/actor/";
    private static final String IMAGE_PATH = "http://localhost:8080/image/actor/";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
      Router router = new Router();
//        HttpSession session = request.getSession();
//        Part part = request.getPart(FILE);
//        String currentPage = request.getHeader(REFERER);
//        Set<String> parameterNames = request.getParameterMap().keySet();
//        Actor actor = new Actor();
//        for (String fieldName : parameterNames) {
//            processFormFields(actor, fieldName, request);
//        }
//        try {
//            processUploadedFile(actor, part);
//            if (actorService.addActor(actor)) {
//                session.setAttribute(ACTOR, actor);
//            } else {
//                session.setAttribute(ACTOR_ALREADY_EXISTS, actor);
//            }
//            router.setRoute(RouteType.REDIRECT);
//            router.setPagePath(currentPage);
//        } catch (ServiceException | InvalidInputException e) {
//            logger.log(Level.ERROR, e);
//        }
       return router;
    }

    private void processFormFields(Actor actor, String fieldName, HttpServletRequest request) {
        switch (fieldName) {
            case FIRST_NAME:
                String firstName = request.getParameter(FIRST_NAME);
                actor = Actor.newActorBuilder().withFirstname(firstName).build();
                break;
            case LAST_NAME:
                String lastName = request.getParameter(LAST_NAME);
                actor = Actor.newActorBuilder().withLastname(lastName).build();
                break;
            case BIRTH_DATE:
//                LocalDate birthDate = LocalDate.of(request.getParameter(BIRTH_DATE));
//                actor = Actor.newActorBuilder().withFirstname(firstName).build();
                break;
        }
    }

//    private String getSavePath(Part part) {
//        String fileName = part.getSubmittedFileName();
//        return (DIRECTORY_PATH + fileName);
//    }
//
//    private String getPicturePath(Part part) {
//        String savePath = getSavePath(part);
//        String pictureName = savePath.substring(savePath.lastIndexOf("/"));
//        return IMAGE_PATH + pictureName;
//    }
//
//    private void processUploadedFile(Actor actor, Part part) throws IOException {
//        String savePath = getSavePath(part);
//        String picturePath = getPicturePath(part);
//        actor.setPicture(picturePath);
//        File file = new File(savePath);
//        part.write(file + File.separator);
//    }
}
