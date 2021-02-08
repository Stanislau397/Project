package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPagePath(PagePath.HOME_PAGE);
        return router;
    }
}
