package edu.epam.project.controller.command.impl.admin;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.entity.Country;
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

import static edu.epam.project.controller.command.RequestParameter.COUNTRY;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

import static edu.epam.project.controller.command.SessionAttribute.COUNTRY_NAME;
import static edu.epam.project.controller.command.SessionAttribute.COUNTRY_SUCCESSFULLY_ADDED;
import static edu.epam.project.controller.command.SessionAttribute.COUNTRY_ALREADY_EXISTS;

public class AddCountryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCountryCommand.class);
    private static final String ADD_COUNTRY_MSG = "Country has been successfully added";
    private static final String COUNTRY_EXISTS_MSG = "Country already exists!";
    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String currentPage = request.getHeader(REFERER);
        String countryName = request.getParameter(COUNTRY);
        Country country = Country.newCountryBuilder()
                .withCountryName(countryName)
                .build();
        try {
            if (movieService.addCountry(country)) {
                session.setAttribute(COUNTRY_SUCCESSFULLY_ADDED, ADD_COUNTRY_MSG);
            } else {
                session.setAttribute(COUNTRY_ALREADY_EXISTS, COUNTRY_EXISTS_MSG);
            }
            session.setAttribute(COUNTRY_NAME, countryName);
            router.setRoute(RouteType.REDIRECT);
            router.setPagePath(currentPage);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}
