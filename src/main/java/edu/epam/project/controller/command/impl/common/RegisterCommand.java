package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.PASSWORD_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.EMAIL_PARAMETER;

public class RegisterCommand implements Command {

    public static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String userPassword = request.getParameter(PASSWORD_PARAMETER);
        String userEmail = request.getParameter(EMAIL_PARAMETER);
        User user = new User();
        user.setUserName(userName);
        user.setEmail(userEmail);
        try {
            if (userService.register(user, userPassword)) {
                router.setPagePath(PagePath.HOME_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.REGISTER_PAGE);
        }
        return router;
    }
}
