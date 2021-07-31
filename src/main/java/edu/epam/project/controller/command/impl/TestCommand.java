package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.AttributeName;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;
import edu.epam.project.entity.Movie;
import edu.epam.project.exception.ServiceException;
import edu.epam.project.service.MovieService;
import edu.epam.project.service.impl.MovieServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestCommand implements Command {

    private MovieService movieService = new MovieServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        try {
            List<Movie> movies = movieService.findAllMovies(0, 25);
            request.setAttribute("movies", movies);
            router.setPagePath("jsp/test.jsp");
        } catch (ServiceException e) {

        }
        return router;
    }
}
