package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Genre;
import edu.epam.project.exception.AlreadyExistsException;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.REFERER;
import static edu.epam.project.controller.command.RequestParameter.GENRE_TITLE_PARAMETER;

import static edu.epam.project.controller.command.SessionAttribute.GENRE_SUCCESSFULLY_ADDED;
import static edu.epam.project.controller.command.SessionAttribute.GENRE_ALREADY_EXISTS;

public class AddGenreCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddGenreCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String genreTitle = request.getParameter(GENRE_TITLE_PARAMETER);
        Genre genre = Genre.newGenreBuilder()
                .withGenreTitle(genreTitle)
                .build();
        try {
            if (movieService.addGenre(genre)) {
                router.setRoute(RouteType.REDIRECT);
                router.setPagePath(currentPage);
                session.setAttribute(GENRE_SUCCESSFULLY_ADDED, genreTitle);
            }
        } catch (ServiceException | AlreadyExistsException e) {
            logger.log(Level.ERROR, e);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
            session.setAttribute(GENRE_ALREADY_EXISTS, e);
        }
        return router;
    }
}
