package ru.job4j.service;

import ru.job4j.SessionFactoryInitializer;
import ru.job4j.model.Item;
import ru.job4j.persistence.ItemStore;

import java.util.List;

public class ToDoService implements Service<Item> {
    private final ItemStore itemStore = new ItemStore(SessionFactoryInitializer.instOf().getSf());

    private static final class Lazy {
        private static final ToDoService INST = new ToDoService();
    }

    public static ToDoService instOf() {
        return ToDoService.Lazy.INST;
    }

    @Override
    public Item add(Item item) {
        return itemStore.add(item);
    }

    @Override
    public List<Item> findAll() {
        return itemStore.findAll();
    }

    public List<Item> findNewItems() {
        return itemStore.findNewItems();
    }

    public List<Item> findCompletedItems() {
        return itemStore.findCompletedItems();
    }

    @Override
    public Item findById(int id) {
        return itemStore.findById(id);
    }

    @Override
    public void update(int id, Item item) {
        itemStore.update(id, item);
    }

    @Override
    public void delete(int id) {
        itemStore.delete(id);
    }
}