package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.User;
import ru.job4j.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    private final UserService userService = UserService.instOf();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String email = req.getParameter("email");
        if (userService.findByEmail(email) != null) {
            resp.sendError(HttpServletResponse.SC_CONFLICT, "user already exists");
        } else {
            User user = new User();
            user.setName(req.getParameter("name"));
            user.setEmail(email);
            user.setPassword(req.getParameter("password"));
            user = userService.add(user);
            ObjectMapper mapper = new ObjectMapper();
            String jsonFromUser = mapper.writeValueAsString(user);
            resp.getWriter().write(jsonFromUser);
        }
    }
}
