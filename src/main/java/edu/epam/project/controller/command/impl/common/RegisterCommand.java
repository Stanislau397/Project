package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.Command;
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

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.PASSWORD_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.EMAIL_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.AttributeName.REGISTER_SUCCESS;
import static edu.epam.project.controller.command.SessionAttribute.REGISTRATION_FAILURE;

public class RegisterCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String DEFAULT_AVATAR = "http://localhost:8080/image/avatar/default_avatar.png";
    private static final String REGISTER_SUCCESS_MSG = "registration was successful";
    private static UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String userPassword = request.getParameter(PASSWORD_PARAMETER);
        String userEmail = request.getParameter(EMAIL_PARAMETER);
        User userToRegister = User.newUserBuilder()
                .withUserName(userName)
                .withEmail(userEmail)
                .withRole(RoleType.USER)
                .withAvatar(DEFAULT_AVATAR)
                .withStatus(false)
                .build();
        try {
            if (userService.register(userToRegister, userPassword)) {
                router.setPagePath(PagePath.LOGIN_PAGE);
                request.setAttribute(REGISTER_SUCCESS, REGISTER_SUCCESS_MSG);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            session.setAttribute(REGISTRATION_FAILURE, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }
}
