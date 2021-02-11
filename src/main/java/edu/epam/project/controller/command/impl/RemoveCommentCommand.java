package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.sevice.MovieService;
import edu.epam.project.sevice.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RemoveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(RemoveCommentCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(RequestParameter.REFERER);
        String comment = request.getParameter(RequestParameter.COMMENT);
        String id = (String) session.getAttribute(SessionAttribute.USER_ID);
        long userId = Long.parseLong(id);
        long movieId = Long.parseLong(request.getParameter(RequestParameter.MOVIE_ID));
        try {
            if (movieService.removeComment(movieId, userId, comment)) {
                router.setPagePath(currentPage);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        }
        return router;
    }
}
