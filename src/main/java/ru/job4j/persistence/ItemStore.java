package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.Item;

import java.util.List;

public class ItemStore implements Store<Item> {
    private final SessionFactory sf;

    public ItemStore(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public void update(int id, Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        item.setId(id);
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findNewItems() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item where created >= date_trunc('DAY', current_date)").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public List<Item> findCompletedItems() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from Item where done = true").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = (Item) session.createQuery("from Item where id = :iid")
                .setParameter("iid", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void delete(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item removableItem = new Item();
        removableItem.setId(id);
        session.delete(removableItem);
        session.getTransaction().commit();
        session.close();
    }
}