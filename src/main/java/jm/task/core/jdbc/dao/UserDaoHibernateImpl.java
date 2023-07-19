package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {}

    @Override
    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS users (" +
                "id SERIAL PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR(50), " +
                "age SMALLINT)";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(query).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(query).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();

        System.out.println("User " + name + " record added successfully!");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        List<User> users = query.getResultList();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String query = "DELETE FROM users";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery(query).executeUpdate();
        transaction.commit();
        session.close();
    }
}
