package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.*;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static edu.epam.project.controller.command.SessionAttribute.USER_ATTR;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.COMMENT_ID;

public class UpVoteCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(UpVoteCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        User user = (User) session.getAttribute(USER_ATTR);
        long userId = user.getUserId();
        long commentId = Long.parseLong(request.getParameter(COMMENT_ID));
        try {
            commentService.upVoteCommentByCommentIdAndUserId(commentId, userId, 1);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
