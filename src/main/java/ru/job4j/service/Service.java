package ru.job4j.service;

import java.util.Collection;

public interface Service<E> {
    E add(E e);

    Collection<E> findAll();

    E findById(int id);

    void update(int id, E e);

    void delete(int id);
}