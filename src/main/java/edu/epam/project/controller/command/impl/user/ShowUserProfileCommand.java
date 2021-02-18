package edu.epam.project.controller.command.impl.user;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.CommentService;
import edu.epam.project.sevice.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;
import static edu.epam.project.controller.command.RequestParameter.COMMENT;

public class ShowUserProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowUserProfileCommand.class);
    private static CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        String userName = (String) session.getAttribute(USER_NAME);
        try {
            int amountOfComments = commentService.countUserCommentsByUserName(userName);
            if (amountOfComments >= 0) {
                router.setRoute(RouteType.FORWARD);
                router.setPagePath(PagePath.USER_PROFILE);
                request.setAttribute(COMMENT, amountOfComments);
                System.out.println(amountOfComments);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(PagePath.HOME_PAGE);
        }
        return router;
    }
}
