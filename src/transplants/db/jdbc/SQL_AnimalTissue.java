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

	
	public void createTable(){
		try{

			Statement stmt8 = dmanager.getC().createStatement();
			String animal_tissues = "CREATE TABLE Animal_tissues "
					   + "(id               INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " typeOfTissue	    TEXT,"
					   + " pathology 		TEXT,"
					   + " time				INTEGER)";
			stmt8.executeUpdate(animal_tissues);
			stmt8.close();
			
			Statement stmt9 = dmanager.getC().createStatement();
			String requested_animals = "CREATE TABLE RequestedOrgan_AnimalsTissues "
					   + "(requested_id     INTEGER  REFERENCES Requested_organs(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " animal_id   		INTEGER  REFERENCES Animal_tissues(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " PRIMARY KEY (requested_id,animal_id))";
			stmt9.executeUpdate(requested_animals);
			stmt9.close();
			
			Statement stmtSeq8 = dmanager.getC().createStatement();
			String sqlSeq8 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Animal_tissues', 1)";
			stmtSeq8.executeUpdate(sqlSeq8);
			stmtSeq8.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm1 = dmanager.getC().createStatement();
			String drop1 = "DROP TABLE Animal_tissues";
			stm1.executeUpdate(drop1);
			stm1.close();
			
			Statement stm2 = dmanager.getC().createStatement();
			String drop2 = "DROP TABLE RequestedOrgan_AnimalsTissues";
			stm2.executeUpdate(drop2);
			stm2.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
