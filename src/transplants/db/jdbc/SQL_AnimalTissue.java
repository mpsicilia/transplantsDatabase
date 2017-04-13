package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_AnimalTissue {

	private DBManager dmanager;
	
	public SQL_AnimalTissue(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//CAMBIARRRR, cambiado
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Insert new record: begin
			Statement stmt = c.createStatement();
			/*String sql = "INSERT INTO Animal_tissues (name, type_of_tissue, pathology,"
					+ " time) VALUES ('" + name + "', '" + type_of_tissue + "',"
					+ " '" + pathology + "', '" + time + "');";*/
			
			
			//stmt.executeUpdate(sql);
			stmt.close();

			// Insert new record: end

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createTable(){
		try{

			Statement stmt8 = dmanager.getC().createStatement();
			String animal_tissues = "CREATE TABLE Animal_tissues "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " type_of_tissue	TEXT,"
					   + " pathology 		TEXT,"
					   + " time				INTEGER)";
			stmt8.executeUpdate(animal_tissues);
			stmt8.close();
			
			Statement stmt9 = dmanager.getC().createStatement();
			String requested_animals = "CREATE TABLE Requested_Animals "
					   + "(requested_id     INTEGER  REFERENCES Requested_organs(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " animal_id   		INTEGER  REFERENCES Animal_tissues(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " PRIMARY KEY (requested_id,animal_id))";
			stmt9.executeUpdate(requested_animals);
			stmt9.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
