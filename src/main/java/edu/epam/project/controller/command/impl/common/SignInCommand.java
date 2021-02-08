package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.UserService;
import edu.epam.project.sevice.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private static UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userEmail = request.getParameter(RequestParameter.EMAIL_PARAMETER);
        String userPassword = request.getParameter(RequestParameter.PASSWORD_PARAMETER);
        User user;
        try {
            Optional<User> userOptional = userService.findByEmailAndPassword(userEmail, userPassword);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                if (!(user.isBlocked())) {
                    RoleType role = user.getRole();
                    String userName = user.getUserName();
                    switch (role) {
                        case ADMIN:
                            session.setAttribute(SessionAttribute.USER_EMAIL, userEmail);
                            session.setAttribute(SessionAttribute.USER_NAME, userName);
                            session.setAttribute(SessionAttribute.ADMIN, String.valueOf(role));
                            router.setPagePath(PagePath.HOME_PAGE);
                            break;
                        case USER:
                            session.setAttribute(SessionAttribute.USER_EMAIL, userEmail);
                            session.setAttribute(SessionAttribute.USER_NAME, userName);
                            session.setAttribute(SessionAttribute.USER, String.valueOf(role));
                            router.setPagePath(PagePath.HOME_PAGE);
                            break;
                        default:
                            session.setAttribute(SessionAttribute.GUEST, String.valueOf(role));
                            router.setPagePath(PagePath.HOME_PAGE);
                    }
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            session.setAttribute(SessionAttribute.SIGN_IN_ERROR, SessionAttribute.SIGN_IN_ERROR_MESSAGE);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.LOGIN_PAGE);
        }
        return router;
    }
}
