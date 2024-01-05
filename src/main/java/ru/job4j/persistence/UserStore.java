package ru.job4j.persistence;

import com.sun.istack.Nullable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.model.User;

import java.util.Collection;

public class UserStore extends AbstractStore<User> {
    public UserStore(SessionFactory sf) {
        super(sf);
    }

    @Override
    public User add(User user) {
        return this.tx(
                session -> {
                    session.save(user);
                    return user;
                }
        );
    }

    @Override
    public void update(int id, User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        user.setId(id);
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Collection<User> findAll() {
        return tx(
                session -> session.createQuery("from User ").list()
        );
    }

    @Nullable
    @Override
    public User findById(int id) {
        return tx(
                session -> session.get(User.class, id)
        );
    }

    @Nullable
    public User findByEmail(String email) {
        return (User) tx(
                session -> session.createQuery("from User where email = :email")
                        .setParameter("email", email)
                        .uniqueResult()
        );
    }

    @Override
    public void delete(int id) {
        this.tx(
                session ->
                        session.createQuery("delete from User where id = :uId")
                                .setParameter("uId", id)
                                .executeUpdate());
    }
}