package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Donor {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			/*String sql = "INSERT INTO Donors (birth_date, weight, height, gender"
					+ "dead_alive, blood_type) VALUES ('" + birth_date + "', '" + weight + "',"
					+ " '" + height + "', '" + gender + "', '" + dead_alive + "', '" + blood_type + "');";
			
			
			stmt.executeUpdate(sql);*/
			stmt.close();
			System.out.println("Department info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
