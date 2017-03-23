package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_AnimalTissues {

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR, cambiado
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Animal_tissues (name, type_of_tissue, pathology,"
					+ " time) VALUES ('" + name + "', '" + type_of_tissue + "',"
					+ " '" + pathology + "', '" + time + "');";
			
			
			stmt.executeUpdate(sql);
			stmt.close();

			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
