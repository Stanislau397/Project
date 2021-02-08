package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.UserService;
import edu.epam.project.sevice.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeEmailCommand implements Command {

    public static final Logger logger = LogManager.getLogger(ChangeEmailCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String oldEmail = request.getParameter(RequestParameter.OLD_EMAIL_PARAMETER);
        String newEmail = request.getParameter(RequestParameter.EMAIL_PARAMETER);
        try {
            if (userService.changeEmail(newEmail, oldEmail)) {
                router.setPagePath(PagePath.USER_PROFILE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.CHANGE_EMAIL);
        }
        return router;
    }
}
