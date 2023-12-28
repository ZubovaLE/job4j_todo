package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Item;
import ru.job4j.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class IndexServlet extends HttpServlet {
    private final ToDoService toDoService = ToDoService.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        String listType = req.getParameter("items");
        List<Item> items;
        if (listType.equals("new")) {
            items = toDoService.findNewItems();
        } else if (listType.equals("done")) {
            items = toDoService.findCompletedItems();
        } else {
            items = toDoService.findAll();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonFromItems = objectMapper.writeValueAsString(items);
        resp.getWriter().write(jsonFromItems);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
