package ru.job4j.service;

import ru.job4j.SessionFactoryInitializer;
import ru.job4j.model.Category;
import ru.job4j.persistence.CategoryStore;

import java.util.Collection;

public class CategoryService implements Service<Category> {
    private final CategoryStore categoryStore = new CategoryStore(SessionFactoryInitializer.instOf().getSf());

    private static final class Lazy {
        private static final CategoryService INST = new CategoryService();
    }

    public static CategoryService instOf() {
        return CategoryService.Lazy.INST;
    }

    @Override
    public Category add(Category category) {
        return categoryStore.add(category);
    }

    @Override
    public Collection<Category> findAll() {
        return categoryStore.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryStore.findById(id);
    }

    @Override
    public void update(int id, Category category) {
        categoryStore.update(id, category);
    }

    @Override
    public void delete(int id) {
        categoryStore.delete(id);
    }
}