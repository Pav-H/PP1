package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class Util {
	// JDBC
    public Connection connectDB() throws SQLException {
	    return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/PP1",
			    "postgres",
			    "nnvq1277");
    }

	// Hibernate
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
//			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
//					.configure("hibernate.cfg.xml")
//					.build();
//			Metadata metadata = new MetadataSources(standardRegistry)
//					.getMetadataBuilder()
//					.build();
//			return metadata.getSessionFactoryBuilder().build();

			Properties properties = new Properties();

			properties.setProperty(Environment.DRIVER, "org.postgresql.Driver");
			properties.setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/PP1");
			properties.setProperty(Environment.USER, "postgres");
			properties.setProperty(Environment.PASS, "nnvq1277");
			properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
			properties.setProperty(Environment.SHOW_SQL, "true");
			properties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			properties.setProperty(Environment.HBM2DDL_AUTO, "");

			sessionFactory = new Configuration()
					.addAnnotatedClass(User.class)
					.addProperties(properties)
					.buildSessionFactory();
			return sessionFactory;
		} catch (Exception e) {
			System.out.println("Initial SessionFactory creation failed." + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
