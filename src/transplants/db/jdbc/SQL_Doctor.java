package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import transplants.db.pojos.Doctor;

public class SQL_Doctor {
	
	public SQL_Doctor(){
		
	}
	
	public String insertDoctor(Doctor doctor){
		String sql="";
		try {
			sql = "INSERT INTO Doctors (registration_number, specialization"
					+ " VALUES ('" + doctor.getRegistrationNumber() + "', '" + doctor.getSpecialization() + "')";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
		
	}
	public void Create_Table() {
		DBManager db=new DBManager ();
		try {
			Statement stmt2 = db.getC().createStatement();
			String doctors = "CREATE TABLE Doctors "
					   + "(id       			INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " registration_number 	TEXT,"
					   + " specialization 		TEXT,"
					   + " hospital_id			INTEGER REFERENCES Hospitals(id))";
			stmt2.executeUpdate(doctors);
			stmt2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	


			

			


}

