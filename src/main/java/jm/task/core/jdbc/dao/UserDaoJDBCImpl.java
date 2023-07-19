package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    public void createUsersTable() throws SQLException {
        try (Connection connection = new Util().connectDB();
             Statement statement = connection.createStatement()) {

            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "lastName VARCHAR(50)," +
                    "age SMALLINT" + ")";

            statement.executeUpdate(query);
            System.out.println("Table 'users' created successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public void dropUsersTable() throws SQLException {
        try (Connection connection = new Util().connectDB();
             Statement statement = connection.createStatement()) {

            String query = "DROP TABLE IF EXISTS users";
            statement.executeUpdate(query);
            System.out.println("Table 'users' dropped successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        String query = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = new Util().connectDB();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (tableExists(connection, "users")) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
                System.out.println("User " + name + " record added successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public void removeUserById(long id) throws SQLException {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = new Util().connectDB();
             PreparedStatement statement = connection.prepareStatement(query)) {

            if (tableExists(connection, "users")) {
                statement.setLong(1, id);
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM users";

        try (Connection connection = new Util().connectDB();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            List<User> users = new ArrayList<>();
            if (tableExists(connection, "users")) {
                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    byte age = resultSet.getByte("age");
                    User user = new User(id, name, lastName, age);
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public void cleanUsersTable() throws SQLException {
        String query = "DELETE FROM users";

        try (Connection connection = new Util().connectDB();
             Statement statement = connection.createStatement()) {

            if (tableExists(connection, "users")) {
                int rowsAffected = statement.executeUpdate(query);
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to connect to the Postgres database!");
            throw e;
        }
    }

    public boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(
                null,
                null,
                tableName,
                null);
        boolean tableExists = resultSet.next();
        resultSet.close();
        return tableExists;
    }
}
