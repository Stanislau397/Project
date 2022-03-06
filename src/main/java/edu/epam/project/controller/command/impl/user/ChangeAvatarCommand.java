package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.USER_ID;
import static edu.epam.project.controller.command.RequestParameter.FILE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.SessionAttribute.*;

public class ChangeAvatarCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeAvatarCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        long userId = Long.parseLong(request.getParameter(USER_ID));
        Part part = request.getPart(FILE);
        String currentPage = request.getHeader(REFERER);
        try {
            if (userService.updateAvatarByUserId(userId, part)) {
                User user = userService.findByUserId(userId);
                session.setAttribute(AVATAR_EDITED, user.getAvatar());
                session.setAttribute(USER_ATTR, user);
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
