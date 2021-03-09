package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.COMMENT;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.SessionAttribute.USER_NAME;

public class LeaveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String userName = (String) session.getAttribute(USER_NAME);
        String comment = request.getParameter(COMMENT);
        String id = request.getParameter(MOVIE_ID);
        long movieId = Long.parseLong(id);
        try {
            if (commentService.leaveComment(userName, movieId, comment)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPagePath(currentPage);
            router.setRoute(RouteType.REDIRECT);
        }
        return router;
    }
}
