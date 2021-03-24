package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class OpenLogInPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router();
        router.setPagePath(PagePath.MOVIE_PAGE);
        return router;
    }
}
