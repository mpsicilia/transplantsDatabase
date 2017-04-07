package transplants.db.jdbc;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transplants.db.pojos.Doctor;

public class SQL_Doctor {
	
	private DBManager dbmanager;
	
	public SQL_Doctor(DBManager dbmanager){
		this.dbmanager= dbmanager;
		dbmanager.connect();
	}
	
	public boolean insertDoctor(Doctor doctor){
		
		try {
			Statement stmt= dbmanager.getC().createStatement();
			String sql = "INSERT INTO Doctors (registration_number, specialization"
					+ " VALUES ('" + doctor.getRegistrationNumber() + "', '" + doctor.getSpecialization() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	public void createTable() {
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
	
	/*public List<Doctor> selectAllDoctors(){//esto esta mal pq es n-n
		try{
		Statement stmt= dbmanager.getC().createStatement();
		String sql= "SELECT * FROM Doctors";
		ResultSet rs= stmt.executeQuery(sql);
		List<Doctor> doctorsList= new ArrayList<Doctor>();
		
		while(rs.next()){
			int id= rs.getInt("id");
			String name= rs.getString("name");
			String registration_number= rs.getString("registration_number");
			String specialization= rs.getString("specialization");
			//we have to create a getHospitals para la foreign kew
	}
	
	
		 
	}catch(SQLException ex){
		ex.printStackTrace();
	}
	}*/

			

			


}

