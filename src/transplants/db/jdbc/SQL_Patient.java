package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Patient {

	private DBManager dmanager;

	public SQL_Patient(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}
	
	public void createTable(){
		try{
			
			Statement stmt3 = dmanager.getC().createStatement();
			String patients = "CREATE TABLE Patients "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " birth_date		DATE,"
					   + " weight 			REAL ,"
					   + " height 			REAL,"
					   + " gender			TEXT,"
					   + " pathology 		TEXT,"
					   + " blood_type		TEXT,"
					   + " addition_date 	DATE,"
					   + " life_expectancy 	INTEGER,"
					   + " hospital_id		INTEGER REFERENCES Hospitals (id))";
			stmt3.executeUpdate(patients);
			stmt3.close();
			
			Statement stmtSeq3 = dmanager.getC().createStatement();
			String sqlSeq3 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Patients', 1)";
			stmtSeq3.executeUpdate(sqlSeq3);
			stmtSeq3.close();
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dmanager.getC().createStatement();
			String drop = "DROP TABLE Patients";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
