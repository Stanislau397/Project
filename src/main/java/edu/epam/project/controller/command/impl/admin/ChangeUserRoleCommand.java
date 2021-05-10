package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
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
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.USER_NAME_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class ChangeUserRoleCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeUserRoleCommand.class);
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        String currentPage = request.getHeader(REFERER);
        Optional<User> userOptional;
        boolean changeRole;
        User user;
        String role;
        try {
            userOptional = userService.findUserByUserName(userName);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                RoleType userRole = user.getRole();
                switch (userRole) {
                    case ADMIN:
                        role = String.valueOf(RoleType.USER);
                        changeRole = userService.changeUserRoleByUserName(userName, role);
                        break;
                    default:
                        role = String.valueOf(RoleType.ADMIN);
                        changeRole = userService.changeUserRoleByUserName(userName, role);
                        break;
                }
                router.setPagePath(currentPage);
                router.setRoute(RouteType.REDIRECT);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
