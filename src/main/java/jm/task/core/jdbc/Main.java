package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            System.out.println("Connected to the PostgreSQL database!");
            System.out.println();

            userService.createUsersTable();
            System.out.println();

            userService.saveUser("Sanya", "Gerasimov", (byte) 34);
            userService.saveUser("Darya", "Ivanova", (byte) 22);
            userService.saveUser("Varush", "Galiev", (byte) 29);
            userService.saveUser("Kevin", "Paramono", (byte) 19);
            System.out.println();

            userService.getAllUsers().forEach(System.out::println);
            System.out.println();

            userService.cleanUsersTable();
            System.out.println();

            userService.dropUsersTable();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
