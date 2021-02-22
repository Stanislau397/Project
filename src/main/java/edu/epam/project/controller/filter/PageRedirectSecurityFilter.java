package edu.epam.project.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(urlPatterns = {"/jsp/*"},
//initParams = {@WebInitParam(name = "INDEX_PATH", value = "index.jsp")})
public class PageRedirectSecurityFilter implements Filter {

    private String homePath;

    public void init(FilterConfig config) throws ServletException {
        homePath = config.getInitParameter("INDEX_PATH");
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
