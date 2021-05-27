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
        String requestURI2 = req.getServletPath()
                .substring(1, req.getServletPath().lastIndexOf('.')).replaceAll("/", "_");
        CommandType commandName = CommandType.valueOf(requestURI2.toUpperCase());
        if (SecurityConfig.isSecurityPage(commandName)) {
        }
        chain.doFilter(request, response);
    }
}
