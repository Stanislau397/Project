package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static edu.epam.project.controller.command.AttributeName.*;
import static edu.epam.project.controller.command.RequestParameter.*;

public class FindAllUsersCommand implements Command {

    public static final Logger logger = LogManager.getLogger(FindAllUsersCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        try {
            List<User> users = userService.findAll();
            int amountOfUsers = userService.countUsers();
            if (users.size() > 0) {
                request.setAttribute(USER_LIST, users);
                request.setAttribute(COUNTER, amountOfUsers);
                router.setPagePath(PagePath.ALL_USERS_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.ADMIN_CABINET_PAGE);
            request.setAttribute(ERROR, ERROR_MESSAGE);
        }
        return router;
    }
}
