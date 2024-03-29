package ru.job4j.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String uri = req.getRequestURI();
        if (uri.endsWith("authorization.do") || uri.endsWith("registration.do")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            resp.setHeader("Location", req.getContextPath() + "/authorization.html");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}