package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.AdminService;
import edu.epam.project.sevice.impl.AdminServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UnblockUserCommand implements Command {

    private static final Logger logger = LogManager.getLogger(BlockUserCommand.class);
    private AdminService adminService = new AdminServiceImpl();
    private static final boolean STATUS = false;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userName = request.getParameter(RequestParameter.USER_NAME_PARAMETER);
        try {
            if (adminService.updateUserStatusByUserName(STATUS, userName)) {
                request.setAttribute(RequestParameter.UNBLOCK, userName + RequestParameter.UNBLOCK_MESSAGE);
            } else {
                request.setAttribute(RequestParameter.ERROR, RequestParameter.ERROR_MESSAGE);
                router.setRoute(RouteType.REDIRECT);
            }
            router.setPagePath(PagePath.BLOCK_USER_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
