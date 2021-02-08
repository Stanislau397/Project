package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.PagePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Router router = new Router();
        router.setPagePath(PagePath.HOME_PAGE);
        router.setRoute(RouteType.REDIRECT);
        session.invalidate();
        return router;
    }
}
