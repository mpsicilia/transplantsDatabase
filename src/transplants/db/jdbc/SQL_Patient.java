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

	private DBManager dmanager;

	public SQL_Patient(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}
	
	public boolean insertPatient (Patient p){
		try{
			Statement stmt = dmanager.getC().createStatement();
			String sql = "INSERT INTO Patients (birth_date, weight, height, gender, pathology, blood_type, "
					+ "addition_date, life_expectancy) VALUES ('" + p.getBirthDate() + "' , '" + p.getWeight() +
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
