package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Request {


	private DBManager dmanager;

	public SQL_Request(DBManager dbmanager) {
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

			/*String sql = "INSERT INTO Requested_organs (name, max_weight, min_weight) "
					+ "VALUES ('" + name + "', '" + max_weight + "', '" + min_weight + "');";
			
			
			stmt.executeUpdate(sql);*/
			stmt.close();
			System.out.println("Department info processed");
			System.out.println("Records inserted.");
			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable (){
		try{
			
			Statement stmt7 = dmanager.getC().createStatement();
			String requested_organs = "CREATE TABLE Requested_organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " max_weight 		REAL ,"
					   + " min_weight 		REAL,"
					   + " patient_id		INTEGER,"
					   + " FOREIGN KEY (patient_id) REFERENCES Patients(id))";
			stmt7.executeUpdate(requested_organs);
			stmt7.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
