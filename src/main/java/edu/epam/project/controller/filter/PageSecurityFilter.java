package edu.epam.project.controller.filter;

import edu.epam.project.controller.command.CommandType;
import edu.epam.project.entity.RoleType;
import edu.epam.project.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "PageSecurityFilter")
public class PageSecurityFilter implements Filter {
    
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String requestURI2 = req.getQueryString();
        int indexOfEqual = requestURI2.indexOf("=") + 1;
        int indexOfAmpersand = requestURI2.indexOf("&");
        String URI2 = requestURI2.substring(indexOfEqual).toUpperCase();
        if (requestURI2.contains("&")) {
            URI2 = requestURI2.substring(indexOfEqual, indexOfAmpersand).toUpperCase();
        }
        CommandType commandName = CommandType.valueOf(URI2);
        if (SecurityConfig.isSecurityPage(commandName)) {
            System.out.println(1);
        }
        chain.doFilter(request, response);
    }
}
