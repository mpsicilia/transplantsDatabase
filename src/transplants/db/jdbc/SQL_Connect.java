package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL_Connect {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplants.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
	
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
