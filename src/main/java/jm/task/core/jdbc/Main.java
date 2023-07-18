package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();

        try {
            System.out.println("Connected to the PostgreSQL database!");
            System.out.println();

            userDao.createUsersTable();
            System.out.println();

            userDao.saveUser("Sanya", "Gerasimov", (byte) 34);
            userDao.saveUser("Darya", "Ivanova", (byte) 22);
            userDao.saveUser("Varush", "Galiev", (byte) 29);
            userDao.saveUser("Kevin", "Paramono", (byte) 19);
            System.out.println();

            userDao.getAllUsers().forEach(System.out::println);
            System.out.println();

            userDao.cleanUsersTable();
            System.out.println();

            userDao.dropUsersTable();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
