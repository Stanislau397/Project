package edu.epam.project.controller;

import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.CommandProvider;
import edu.epam.project.controller.command.impl.common.EmptyCommand;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

import static edu.epam.project.controller.RouteType.FORWARD;
import static edu.epam.project.controller.command.RequestParameter.COMMAND;

@WebServlet(urlPatterns = {"/UploadServlet"})
@MultipartConfig(maxRequestSize = 1024 * 1024 * 5,
        maxFileSize = 1024 * 1024 * 5 * 5, fileSizeThreshold = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandParameter = request.getParameter(COMMAND);
        Optional<Command> commandOptional =
                CommandProvider.defineCommand(commandParameter);
        Command command = commandOptional.orElse(new EmptyCommand());
        Router page = command.execute(request);
        RouteType routeType = page.getRoute();
        String currentPage = page.getPagePath();
        if (routeType == FORWARD) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(currentPage);
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(currentPage);
        }
    }
}