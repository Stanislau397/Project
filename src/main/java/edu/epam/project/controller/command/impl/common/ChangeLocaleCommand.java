package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.RouteType;
import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static edu.epam.project.controller.command.RequestParameter.LANGUAGE;
import static edu.epam.project.controller.command.RequestParameter.REFERER;

public class ChangeLocaleCommand implements Command {

    private static final String RU = "ru";
    private static final String EN = "en";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String language = request.getParameter(LANGUAGE);
        HttpSession session = request.getSession();
        String pagePath = request.getHeader(REFERER);
        switch (language) {
            case EN:
                session.setAttribute(LANGUAGE, EN);
                break;
            default:
                session.setAttribute(LANGUAGE, RU);
        }
        router.setRoute(RouteType.REDIRECT);
        router.setPagePath(pagePath);
        return router;
    }
}
