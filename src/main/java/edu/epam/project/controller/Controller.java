package edu.epam.project.controller;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.CommandProvider;
import edu.epam.project.controller.command.impl.common.EmptyCommand;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

import static edu.epam.project.controller.command.RequestParameter.COMMAND;

public class Controller extends HttpServlet {

    public static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        String commandParameter = request.getParameter(COMMAND);
        Optional<Command> commandOptional =
                CommandProvider.defineCommand(commandParameter);
        Command command = commandOptional.orElse(new EmptyCommand());
        Router page = command.execute(request);
        RouteType routeType = page.getRoute();
        String currentPage = page.getPagePath();
        switch (routeType) {
            case INCLUDE:
                RequestDispatcher dispatcher = request.getRequestDispatcher(currentPage);
                dispatcher.include(request, response);
                break;
            case FORWARD:
                dispatcher = request.getRequestDispatcher(currentPage);
                dispatcher.forward(request, response);
                break;
            default:
                response.sendRedirect(currentPage);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool connectionPool = ConnectionPool.INSTANCE;
        connectionPool.destroyPool();
    }
}
