package ru.job4j.controller;

import ru.job4j.model.Category;
import ru.job4j.model.Item;
import ru.job4j.model.User;
import ru.job4j.service.CategoryService;
import ru.job4j.service.Service;
import ru.job4j.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class EditServlet extends HttpServlet {
    private final Service<Item> toDoService = ToDoService.instOf();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int id = Integer.parseInt(req.getParameter("id"));
        if (id != 0) {
            Item itemForUpdating = toDoService.findById(id);
            itemForUpdating.setName(name);
            itemForUpdating.setDescription(description);
            toDoService.update(id, itemForUpdating);
        } else {
            User user = (User) req.getSession().getAttribute("user");
            var categories = req.getParameter("categories");
            Item item = new Item(0, name, description, new Timestamp(System.currentTimeMillis()), false, user);
            toDoService.add(item);
        }
    }
}