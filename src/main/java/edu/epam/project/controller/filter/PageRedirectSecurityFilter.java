package edu.epam.project.controller.filter;

import edu.epam.project.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectSecurityFilter implements Filter {

    private String homePath;

    public void init(FilterConfig config) throws ServletException {
        homePath = config.getInitParameter(PagePath.HOME_PAGE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpRequest.getContextPath() + homePath);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
