package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static edu.epam.project.controller.command.RequestParameter.MOVIE_ID;
import static edu.epam.project.controller.command.RequestParameter.COUNTRIES;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class AddCountryToMovieCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCountryToMovieCommand.class);
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String currentPage = request.getHeader(REFERER);
        long movieId = Long.parseLong(request.getParameter(MOVIE_ID));
        String[] countries = request.getParameterValues(COUNTRIES);
        try {
            for (String country : countries) {
                long countryId = Long.parseLong(country);
                movieService.addCountryToMovieByMovieIdAndCountryId(movieId, countryId);
            }
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
