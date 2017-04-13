package transplants.db.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Doctor;

public class SQL_Doctor {
	
	private DBManager dbManager;
	
	public SQL_Doctor(DBManager dbmanager){
		this.dbManager= dbmanager;
		dbmanager.connect();
	}
	
	public boolean insertDoctor(Doctor doctor){		
		try {
			Statement stmt= dbManager.getC().createStatement();
			String sql = "INSERT INTO Doctors (name, registrationNumber, specialization)"
					+ " VALUES ('" + doctor.getNameOfDoctor() + "', '" + doctor.getRegistrationNumber() + "', "
					+ "'" + doctor.getSpecialization() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	
	public List<Doctor> searchDoctor(String nameDoct) {
		List<Doctor> lookForDoctor = new ArrayList<Doctor>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Doctors WHERE name LIKE '%" + nameDoct + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameDoctor = rs.getString("name");
				String regNumber = rs.getString("registrationNumber");
				String specializ = rs.getString("specialization");
				Doctor doctorToShow = new Doctor(id, nameDoctor, regNumber, specializ);
				lookForDoctor.add(doctorToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForDoctor;
	}
	
	public boolean updateDoctor (Doctor doct){		
		try {
			String sql = "UPDATE Doctors SET name=?, registrationNumber=?, specialization=?"
					+ " WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, doct.getNameOfDoctor());
			prep.setString(2, doct.getRegistrationNumber());
			prep.setString(3, doct.getSpecialization());
			prep.setInt(4, doct.getId());
			prep.executeUpdate();
			prep.close();			
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public boolean deleteDoctor (Doctor doctor){
		try{
			String sql = "DELETE FROM Doctors WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, doctor.getId());
			prep.executeUpdate(sql);
			prep.close();			
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Doctor> selectAllDoctors() {
		List<Doctor> lookForDoctor = new ArrayList<Doctor>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Doctors";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String nameDoctor = rs.getString("name");
				String regNumber = rs.getString("registrationNumber");
				String specializ = rs.getString("specialization");
				Doctor doctorToShow = new Doctor(id, nameDoctor,regNumber,specializ);
				lookForDoctor.add(doctorToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForDoctor;
	}

	
	public void createTable() {
		try {
			Statement stmt2 = dbManager.getC().createStatement();
			String doctors = "CREATE TABLE Doctors "
					   + "(id       			INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name                 TEXT NOT NULL"
					   + " registrationNumber 	TEXT NOT NULL,"
					   + " specialization 		TEXT,"
					   + " hospital_id			INTEGER REFERENCES Hospitals(id))";
			stmt2.executeUpdate(doctors);
			stmt2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

