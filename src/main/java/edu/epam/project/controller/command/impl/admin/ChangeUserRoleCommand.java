package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.*;

import static edu.epam.project.controller.command.SessionAttribute.CHANGED_ROLE;
import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class ChangeUserRoleCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        long userId = Long.parseLong(request.getParameter(USER_ID));
        String userName = request.getParameter(USER_NAME);
        String role = request.getParameter(ROLE_PARAMETER);
        String currentPage = request.getHeader(REFERER);
        try {
            if (userService.updateRoleByUserId(userId, role)) {
                session.setAttribute(CHANGED_ROLE, userName);
                router.setPagePath(currentPage);
                router.setRoute(RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
