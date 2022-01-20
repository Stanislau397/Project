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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static edu.epam.project.controller.command.SessionAttribute.USER_ID;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.COMMENT_ID;

public class DownVoteCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(DownVoteCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        long  userId = (Long) session.getAttribute(USER_ID);
        long commentId = Long.parseLong(request.getParameter(COMMENT_ID));
        boolean userAlreadyDownVoted;
        try {
            userAlreadyDownVoted = commentService.userAlreadyDownVoted(commentId, userId, 1);
            if (!userAlreadyDownVoted) {
                commentService.downVoteComment(commentId, userId, 1);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
