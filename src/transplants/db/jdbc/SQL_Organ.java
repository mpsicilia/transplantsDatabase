package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Organ {
	
	private DBManager dmanager;

	public SQL_Organ(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			/*String sql = "INSERT INTO Organs (name, weight, type_of_donation) "
					+ " VALUES ('" + name + "', '" + weight + "',"
					+ " '" + type_of_donation + "');";
			
			
			stmt.executeUpdate(sql);*/
			stmt.close();
			System.out.println("Department info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(){
		try{
			
			Statement stmt6 = dmanager.getC().createStatement();
			String organs = "CREATE TABLE Organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " weight 			REAL ,"
					   + " type_of_donation	TEXT,"
					   + " requested_id		INTEGER,"
					   + " donor_id			INTEGER, "
					   + " FOREIGN KEY (requested_id) REFERENCES Requested_organs(id),"
					   + " FOREIGN KEY (donor_id) REFERENCES Donors (id))";
			stmt6.executeUpdate(organs);
			stmt6.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
