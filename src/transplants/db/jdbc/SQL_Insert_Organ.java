package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Insert_Organ {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Organs (name, weight, type_of_donation) "
					+ " VALUES ('" + name + "', '" + weight + "',"
					+ " '" + type_of_donation + "');";
			
			
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Department info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
