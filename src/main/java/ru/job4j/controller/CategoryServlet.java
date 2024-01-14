package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.Category;
import ru.job4j.service.CategoryService;
import ru.job4j.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    private final Service<Category> categoryService = CategoryService.instOf();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=utf-8");
        List<Category> categories = (List<Category>) categoryService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        String jsonFromCategories = mapper.writeValueAsString(categories);
        resp.getWriter().write(jsonFromCategories);
    }
}