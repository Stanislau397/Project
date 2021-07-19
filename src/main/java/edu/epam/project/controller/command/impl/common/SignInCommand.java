package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.*;
import static edu.epam.project.controller.command.SessionAttribute.USER_ID;
import static edu.epam.project.controller.command.SessionAttribute.USER_EMAIL;
import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;
import static edu.epam.project.controller.command.SessionAttribute.ADMIN;
import static edu.epam.project.controller.command.SessionAttribute.USER;
import static edu.epam.project.controller.command.SessionAttribute.SIGN_IN_ERROR;
import static edu.epam.project.controller.command.SessionAttribute.USER_AVATAR;
import static edu.epam.project.controller.command.SessionAttribute.SIGN_IN_ERROR_MESSAGE;
import static edu.epam.project.controller.command.SessionAttribute.GUEST;

public class SignInCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userEmail = request.getParameter(EMAIL_PARAMETER);
        String userPassword = request.getParameter(PASSWORD_PARAMETER);
        User user;
        try {
            Optional<User> userOptional = userService.findByEmailAndPassword(userEmail, userPassword);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (!(user.isBlocked())) {
                    RoleType role = user.getRole();
                    String userName = user.getUserName();
                    String userAvatar = user.getAvatar();
                    long userId = user.getUserId();
                    switch (role) {
                        case ADMIN:
                            session.setAttribute(USER_ID, userId);
                            session.setAttribute(USER_EMAIL, userEmail);
                            session.setAttribute(USER_NAME, userName);
                            session.setAttribute(ADMIN, String.valueOf(role));
                            session.setAttribute(USER_AVATAR, userAvatar);
                            router.setPagePath(PagePath.INDEX);
                            break;
                        case USER:
                            session.setAttribute(USER_ID, userId);
                            session.setAttribute(USER_EMAIL, userEmail);
                            session.setAttribute(USER_NAME, userName);
                            session.setAttribute(USER, String.valueOf(role));
                            session.setAttribute(USER_AVATAR, userAvatar);
                            router.setPagePath(PagePath.INDEX);
                            break;
                        default:
                            session.setAttribute(GUEST, String.valueOf(role));
                            router.setPagePath(PagePath.INDEX);
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            request.setAttribute(SIGN_IN_ERROR, SIGN_IN_ERROR_MESSAGE);
            router.setRoute(RouteType.FORWARD);
            router.setPagePath(PagePath.LOGIN_PAGE);
        }
        return router;
    }
}
