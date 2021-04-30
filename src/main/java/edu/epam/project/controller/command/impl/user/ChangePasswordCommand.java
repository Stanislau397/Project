package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.PASSWORD_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.NEW_PASSWORD;

import static edu.epam.project.controller.command.SessionAttribute.CHANGE_PASSWORD;
import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class ChangePasswordCommand implements Command {

    public static final Logger logger = LogManager.getLogger(ChangePasswordCommand.class);
    private static final String PASSWORD_HAS_BEEN_UPDATED = "Password has been updated";
    private static final String INCORRECT_PASSWORD = "Incorrect password";
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String userName = (String) session.getAttribute(USER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String newPassword = request.getParameter(NEW_PASSWORD);
        User user = new User();
        user.setUserName(userName);
        try {
            if (userService.changePassword(user, password, newPassword)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
                session.setAttribute(CHANGE_PASSWORD, PASSWORD_HAS_BEEN_UPDATED);
            } else {
                session.setAttribute(CHANGE_PASSWORD, INCORRECT_PASSWORD);
                router.setPagePath(currentPage);
                router.setRoute(RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            session.setAttribute(CHANGE_PASSWORD, INCORRECT_PASSWORD);
            router.setPagePath(currentPage);
            router.setRoute(RouteType.REDIRECT);
        }
        return router;
    }
}
