package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.*;
import static edu.epam.project.controller.command.SessionAttribute.COMMENT_REMOVED;

public class RemoveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long userId = Long.parseLong(request.getParameter(USER_ID));
        long commentId = Long.parseLong(request.getParameter(COMMENT_ID));
        try {
            if (commentService.deleteByCommentIdAndUserId(commentId, userId)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
                session.setAttribute(COMMENT_REMOVED, 1);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }
}
