package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static edu.epam.project.controller.command.RequestParameter.KEY_WORD_PARAMETER;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.AttributeName.MOVIES_BY_KEY_WORD_LIST;

public class SearchCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SearchCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String keyWord = request.getParameter(KEY_WORD_PARAMETER);
        String currentPage = request.getHeader(REFERER);
        try {
            List<Movie> moviesByKeyWord = movieService.findMoviesByKeyWord(keyWord);
            if (moviesByKeyWord.size() > 0) {
                request.setAttribute(MOVIES_BY_KEY_WORD_LIST, moviesByKeyWord);
                router.setPagePath(PagePath.MOVIE_PAGE);
            } else {
                router.setRoute(RouteType.REDIRECT);
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
