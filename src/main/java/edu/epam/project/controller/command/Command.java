package edu.epam.project.controller.command;

import edu.epam.project.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);
}
