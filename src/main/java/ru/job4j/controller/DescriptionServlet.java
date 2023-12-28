package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Item;
import ru.job4j.service.Service;
import ru.job4j.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DescriptionServlet extends HttpServlet {
    private final Service<Item> toDoService = ToDoService.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Item item = toDoService.findById(id);
        ObjectMapper mapper = new ObjectMapper();
        String jsonFromItem = mapper.writeValueAsString(item);
        resp.getWriter().write(jsonFromItem);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Item item = toDoService.findById(id);
        if (req.getParameter("delete") != null) {
            toDoService.delete(id);
        } else {
            item.setDone(true);
            toDoService.update(id, item);
        }
    }
}