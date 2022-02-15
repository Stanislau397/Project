package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.RoleType;
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

import static edu.epam.project.controller.command.AttributeName.USER_ROLE;
import static edu.epam.project.controller.command.AttributeName.ADMIN_ROLE;
import static edu.epam.project.controller.command.AttributeName.USER;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;

public class SearchUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SearchUserCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        try {
            User user = userService.findByUserName(userName);
            RoleType role = user.getRole();
            request.setAttribute(USER, user);
            switch (role) {
                case ADMIN:
                    request.setAttribute(ADMIN_ROLE, role);
                    break;
                default:
                    request.setAttribute(USER_ROLE, role);
                    break;
            }
            router.setPagePath(PagePath.ALL_USERS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
