package ru.job4j.controller;

import ru.job4j.model.User;
import ru.job4j.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    private final UserService userService = UserService.instOf();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.findByEmail(req.getParameter("email"));
        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession sc = req.getSession();
            sc.setAttribute(String.valueOf(user.getId()), user);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(422, "Invalid username or password");
        }
    }
}
