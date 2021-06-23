package edu.epam.project.controller.command.impl;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

public class TestCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        String[] value = request.getParameterValues("value");
        System.out.println(Arrays.toString(value));
        router.setPagePath(PagePath.MOVIE_PAGE);
        return router;
    }
}
