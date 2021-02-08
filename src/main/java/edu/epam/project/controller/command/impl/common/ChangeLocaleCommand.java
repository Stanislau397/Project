package edu.epam.project.controller.command.impl.common;

import edu.epam.project.controller.Router;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession();
        String language = request.getParameter(RequestParameter.LANGUAGE);
        session.setAttribute(RequestParameter.LANGUAGE, language);
        String currentPage = (String) session.getAttribute(SessionAttribute.CURRENT_PAGE);
        return null;
    }
}
