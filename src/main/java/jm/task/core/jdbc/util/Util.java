package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    public Connection connectDB() throws SQLException {
	    return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/PP1",
			    "postgres",
			    "nnvq1277");
    }
}
