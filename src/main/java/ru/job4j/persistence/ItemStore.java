package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.Item;

import java.util.List;

public class ItemStore extends AbstractStore<Item> {

    public ItemStore(SessionFactory sf) {
        super(sf);
    }

    @Override
    public Item add(Item item) {
        return this.tx(
                session -> {
                    session.save(item);
                    return item;
                }
        );
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
        return this.tx(
                session -> session.createQuery("select distinct i from Item i inner join fetch i.categories c")
                        .list()
        );
    }

    public List<Item> findNewItems() {
        return this.tx(
                session -> session
                        .createQuery("select distinct i from Item i inner join fetch i.categories c " +
                                "where i.created >= date_trunc('DAY', current_date)").list()
        );
    }

    public List<Item> findCompletedItems() {
        return this.tx(
                session -> session.createQuery("select distinct i from Item i inner join fetch i.categories c " +
                        "where i. done = true").list()
        );
    }

    @Override
    public Item findById(int id) {
        return this.tx(
                session -> session.createQuery("select distinct i from Item i inner join fetch i.categories c " +
                                "where i.id = :iid", Item.class)
                        .setParameter("iid", id)
                        .uniqueResult()
        );
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