package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.Category;

import java.util.Collection;

public class CategoryStore extends AbstractStore<Category> implements Store<Category>{
    public CategoryStore(SessionFactory sf) {
        super(sf);
    }

    @Override
    public Category add(Category category) {
        return this.tx(
                session -> {
                    session.save(category);
                    return category;
                }
        );
    }

    @Override
    public void update(int id, Category category) {
        Session session = sf.openSession();
        session.beginTransaction();
        category.setId(id);
        session.update(category);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection<Category> findAll() {
        return this.tx(
                session -> session.createQuery("from Category ").list()
        );
    }

    @Override
    public Category findById(int id) {
        return this.tx(
                session -> session.get(Category.class, id)
        );
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Category removableCategory = new Category();
        removableCategory.setId(id);
        session.delete(removableCategory);
        session.getTransaction().commit();
        session.close();
    }
}