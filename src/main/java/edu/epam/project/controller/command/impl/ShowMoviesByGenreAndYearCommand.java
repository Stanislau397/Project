package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ShowMoviesByGenreAndYearCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        return router;
    }
}
