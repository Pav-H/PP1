package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Util {
	// JDBC
    public Connection connectDB() throws SQLException {
	    return DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/PP1",
			    "postgres",
			    "nnvq1277");
    }

	// Hibernate
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
					.configure("hibernate.cfg.xml")
					.build();
			Metadata metadata = new MetadataSources(standardRegistry)
					.getMetadataBuilder()
					.build();
			return metadata.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			System.out.println("Initial SessionFactory creation failed." + e.getMessage());
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
