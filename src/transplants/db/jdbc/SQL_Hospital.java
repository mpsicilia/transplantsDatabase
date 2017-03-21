package transplants.db.jdbc;

import java.io.*;
import java.sql.*;

public class SQL_Insert_Hospital {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR, cambiado
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO Hospitals (name, phone_number, address, city"
					+ "postcode, country) VALUES ('" + name + "', '" + phone_number + "',"
					+ " '" + address + "', '" + city + "', '" + postcode + "', '" + country + "');";
			
			
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
