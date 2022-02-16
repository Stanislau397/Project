package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.EMAIL_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.PASSWORD_PARAMETER;

import static edu.epam.project.controller.command.ErrorMessage.LOGIN_ERROR_MSG;

import static edu.epam.project.controller.command.AttributeName.SIGN_IN_ERROR;

import static edu.epam.project.controller.command.SessionAttribute.USER_ATTR;

public class SignInCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String userEmail = request.getParameter(EMAIL_PARAMETER);
        String userPassword = request.getParameter(PASSWORD_PARAMETER);
        try {
            User user = userService.findByEmailAndPassword(userEmail, userPassword);
            if (!user.getIsLocked()) {
                session.setAttribute(USER_ATTR, user);
                router.setPagePath(PagePath.INDEX);
            }
        } catch (ServiceException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute(SIGN_IN_ERROR, LOGIN_ERROR_MSG);
            router.setRoute(RouteType.FORWARD);
            router.setPagePath(PagePath.LOGIN_PAGE);
        }
        return router;
    }
}
