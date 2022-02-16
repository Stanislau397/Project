package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.SessionAttribute;
import edu.epam.project.entity.Comment;
import edu.epam.project.entity.Movie;
import edu.epam.project.entity.User;
import edu.epam.project.exception.InvalidInputException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.CommentService;
import edu.epam.project.service.impl.CommentServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.util.Date;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.TEXT_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;

public class LeaveCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveCommentCommand.class);
    private CommentService commentService = new CommentServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String text = request.getParameter(TEXT_PARAMETER);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        User user = (User) session.getAttribute(SessionAttribute.USER_ATTR);
        Timestamp postDate = new Timestamp(new Date().getTime());
        System.out.println(postDate);
        Movie movie = new Movie();
        movie.setMovieId(movieId);
        System.out.println(movie.getMovieId());
        Comment comment = Comment.newCommentBuilder()
                .withUser(user)
                .withMovie(movie)
                .withText(text)
                .withPostDate(postDate)
                .build();
        try {
            if (commentService.addComment(comment)) {
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
