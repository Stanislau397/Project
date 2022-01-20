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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.*;
import static edu.epam.project.controller.command.SessionAttribute.BLOCKED_USER;
import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class BlockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long userId = Long.parseLong(request.getParameter(USER_ID));
        String userName = request.getParameter(USER_NAME);
        try {
            if (userService.updateStatusById(true, userId)) {
                session.setAttribute(BLOCKED_USER, userName);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
