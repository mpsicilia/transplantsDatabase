package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Insert_Animal {

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplants.db");//CAMBIARRRR
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO animal (name, tissue_type, patient_pathology,"
					+ " time_that_lasts) VALUES ('" + name + "', '" + tissue_type + "',"
					+ " '" + patient_pathology + "', '" + time_that_lasts + "');";
			
			
			stmt.executeUpdate(sql);
			stmt.close();

			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
