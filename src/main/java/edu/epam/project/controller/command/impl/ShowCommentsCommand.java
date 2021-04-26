package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Comment;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.AttributeName.COMMENTS_LIST;

public class ShowCommentsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowCommentsCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            List<Comment> comments = commentService.findCommentsByMovieId(movieId);
            if (comments.size() > 0) {
                request.setAttribute(COMMENTS_LIST, comments);
                router.setPagePath(PagePath.MOVIE_DETAIL_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
