package edu.epam.project.controller;

import edu.epam.project.connection.ConnectionPool;
import edu.epam.project.controller.command.Command;
import edu.epam.project.controller.command.CommandProvider;
import edu.epam.project.controller.command.RequestParameter;
import edu.epam.project.controller.command.impl.common.EmptyCommand;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

public class Controller extends HttpServlet {

    public static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Command> commandOptional =
                CommandProvider.defineCommand(request.getParameter(RequestParameter.COMMAND_PARAMETER));
        Command command = commandOptional.orElse(new EmptyCommand());
        Router page = command.execute(request);
        String currentPage = page.getPagePath();
        logger.log(Level.INFO, currentPage);

        //String currentPage = command.execute(request);
        if (page.getRoute().equals(RouteType.FORWARD)) {
            request.getRequestDispatcher(currentPage).forward(request, response);
        } else {
            response.sendRedirect(currentPage);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.INSTANCE.destroyPool();
    }
}
