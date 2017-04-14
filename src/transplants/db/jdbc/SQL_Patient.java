package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class SQL_Patient {

	private DBManager dbManager;

	public SQL_Patient(DBManager dbmanager) {
		this.dbManager = dbmanager;
		dbManager.connect();
	}
	
	public boolean insertPatient (Patient p){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Patients (name, birthDate, weight, height, gender, pathology, bloodType, "
					+ "additionDate, lifeExpectancy) VALUES ('"+ p.getName() + "','" + p.getBirthDate() + "' , '" + p.getWeight() +
					"' , '" + p.getHeight() + "' , '" + p.getGender() + "' , '" + p.getPathology() + "' , '" +
					p.getBloodType() + "' , '" + p.getAdditionDate() + "' , '" + p.getLifeExpectancy() + "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Patient> searchPatient(String patho) {
		
		try {
			

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public void createTable(){
		try{			
			Statement stmt3 = dbManager.getC().createStatement();
			String patients = "CREATE TABLE Patients "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name             TEXT NOT NULL,"
					   + " birthDate		DATE NOT NULL,"
					   + " weight 			REAL ,"
					   + " height 			REAL,"
					   + " gender			TEXT,"
					   + " pathology 		TEXT,"
					   + " bloodType		TEXT,"
					   + " additionDate 	DATE,"
					   + " lifeExpectancy 	INTEGER,"
					   + " hospital_id		INTEGER REFERENCES Hospitals (id))";
			stmt3.executeUpdate(patients);
			stmt3.close();
			
			Statement stmtSeq3 = dbManager.getC().createStatement();
			String sqlSeq3 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Patients', 1)";
			stmtSeq3.executeUpdate(sqlSeq3);
			stmtSeq3.close();
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dbManager.getC().createStatement();
			String drop = "DROP TABLE Patients";
			stm.executeUpdate(drop);
			stm.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
