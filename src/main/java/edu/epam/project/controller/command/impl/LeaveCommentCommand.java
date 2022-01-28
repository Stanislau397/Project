package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.TEXT_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.USER_ID;

public class LeaveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        long userId = Long.parseLong(request.getParameter(USER_ID));
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        String text = request.getParameter(TEXT_PARAMETER);
        try {
            if (commentService.add(userId, movieId, text)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
            }
        } catch (ServiceException | InvalidInputException e) {
            logger.log(Level.ERROR, e);
            router.setPagePath(currentPage);
            router.setRoute(RouteType.REDIRECT);
        }
        return router;
    }
}
