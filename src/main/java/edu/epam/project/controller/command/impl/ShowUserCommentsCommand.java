package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.UserService;
import edu.epam.project.service.impl.CommentServiceImpl;
import edu.epam.project.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Optional;

import static edu.epam.project.controller.command.AttributeName.*;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class ShowUserCommentsCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ShowUserCommentsCommand.class);
    private CommentService commentService = new CommentServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String userName = request.getParameter(USER_NAME);
        try {
            List<Movie> comments = commentService.findCommentsByUserName(userName);
            Optional<User> userOptional = userService.findUserByUserName(userName);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                request.setAttribute(MOVIE_LIST, comments);
                request.setAttribute(AttributeName.USER, user);
                router.setPagePath(PagePath.EDIT_USER);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
