package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
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

public class ToEditUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ToEditUserCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String userName = request.getParameter(RequestParameter.USER_NAME_PARAMETER);
        User user;
        try {
            Optional<User> userOptional = userService.findUserByUserName(userName);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                request.setAttribute(AttributeName.USER, user);
                request.setAttribute(AttributeName.USER_NAME, user);
                router.setPagePath(PagePath.EDIT_USER);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
