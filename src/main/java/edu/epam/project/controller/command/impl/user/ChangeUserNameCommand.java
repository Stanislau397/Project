package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.NEW_USER_NAME;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class ChangeUserNameCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String newUserName = request.getParameter(NEW_USER_NAME);
        try {
            if (userService.changeUserName(userName, newUserName)) {
                session.removeAttribute(USER_NAME);
                session.setAttribute(USER_NAME, newUserName);
                router.setPagePath(PagePath.USER_PROFILE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.CHANGE_USER_NAME);
        }
        return router;
    }
}
