package edu.epam.project.controller.command.impl.user;

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

import static edu.epam.project.controller.command.RequestParameter.*;

import static edu.epam.project.controller.command.SessionAttribute.COMMENT_EDITED;

public class EditCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditCommentCommand.class);
    private static final String COMMENT_EDITED_MSG = "Comment has been edited";
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String newText = request.getParameter(UPDATED_TEXT);
        long commentId = Long.parseLong(request.getParameter(COMMENT_ID));
        long userId = Long.parseLong(request.getParameter(USER_ID));
        try {
            if (commentService.update(userId, commentId, newText)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
                session.setAttribute(COMMENT_EDITED, COMMENT_EDITED_MSG);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
