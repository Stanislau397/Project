package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.UserService;
import edu.epam.project.sevice.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.*;
import static edu.epam.project.controller.command.SessionAttribute.*;

public class ChangePasswordCommand implements Command {

    public static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(USER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String newPassword = request.getParameter(NEW_PASSWORD);
        logger.log(Level.INFO, userName);
        User user = new User();
        user.setUserName(userName);
        try {
            if (userService.changePassword(user, password, newPassword)) {
                router.setPagePath(PagePath.ADMIN_CABINET_PAGE);
                request.setAttribute(CHANGE_PASSWORD, CHANGE_PASSWORD_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute(CHANGE_PASSWORD, CHANGE_PASSWORD_ERROR);
            router.setPagePath(PagePath.CHANGE_PASSWORD);
            router.setRoute(RouteType.REDIRECT);
        }
        return router;
    }
}
