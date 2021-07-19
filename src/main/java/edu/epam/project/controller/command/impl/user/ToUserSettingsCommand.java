package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;

import static edu.epam.project.controller.command.AttributeName.USER;

public class ToUserSettingsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToUserSettingsCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        try {
            Optional<User> userInfo = userService.findUserByUserName(userName);
            if (userInfo.isPresent()) {
                User user = userInfo.get();
                request.setAttribute(USER, user);
                router.setPagePath(PagePath.USER_SETTINGS);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
