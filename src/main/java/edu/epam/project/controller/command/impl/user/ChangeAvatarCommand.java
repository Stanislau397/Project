package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.USER_ID;
import static edu.epam.project.controller.command.RequestParameter.FILE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class ChangeAvatarCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeAvatarCommand.class);
    private static final String DIRECTORY_PATH = "C:/project/src/main/webapp/css/image/avatar/";
    private static final String SEPARATOR = "/";
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        long userId = Long.parseLong(request.getParameter(USER_ID));
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        String avatar = getPicturePath(part);
        try {
            if (userService.updateUserAvatarById(userId, avatar)) {
                processUploadedFile(part);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
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

    private void processUploadedFile(Part part) throws IOException {
        String savePath = getSavePath(part);
        File file = new File(savePath);
        part.write(file + File.separator);
    }
}
