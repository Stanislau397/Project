package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.AdminService;
import edu.epam.project.service.impl.AdminServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.*;

public class UnblockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private AdminService adminService = new AdminServiceImpl();
    private static final boolean STATUS = false;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME_PARAMETER);
        try {
            if (adminService.updateUserStatusByUserName(STATUS, userName)) {
                request.setAttribute(UNBLOCK, userName + UNBLOCK_MESSAGE);
            } else {
                request.setAttribute(ERROR, ERROR_MESSAGE);
                router.setRoute(RouteType.REDIRECT);
            }
            router.setPagePath(PagePath.BLOCK_USER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
