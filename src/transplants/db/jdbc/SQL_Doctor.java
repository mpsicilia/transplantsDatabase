package transplants.db.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Doctor;
import transplants.db.pojos.Patient;

public class SQL_Doctor {
	
	private DBManager dbManager;
	
	public SQL_Doctor(DBManager dbmanager){
		this.dbManager= dbmanager;
		dbManager.connect();
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
	public Integer getIdOfLastDoctor(Doctor doctor){
		Doctor doctorToShow= new Doctor();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT name, registrationNumber, specialization FROM Doctors "
					+ "WHERE name LIKE '%" + doctor.getNameOfDoctor() + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nameDoctor = rs.getString("name");
				String regNumber = rs.getString("registrationNumber");
				String specializ = rs.getString("specialization");
				doctorToShow = new Doctor(id, nameDoctor, regNumber, specializ);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return doctorToShow.getId();
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
			prep.executeUpdate();
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

	public List<Doctor> doctorsOfPatient (String patName){
		List<Doctor> patDoctors = new ArrayList<Doctor>();
		try{
			Statement stmt = dbManager.getC().createStatement();
			//sql que seleccione doctores segun el nombre del paciente
		}catch (Exception e){
			e.printStackTrace();
		}
		return patDoctors;
	}
	
	public void createTable() {
		try {
			Statement stmt1 = dbManager.getC().createStatement();
			String doctors = "CREATE TABLE Doctors "
					   + "(id       			INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name                 TEXT NOT NULL,"
					   + " registrationNumber 	TEXT NOT NULL,"
					   + " specialization 		TEXT)";
			stmt1.executeUpdate(doctors);
			stmt1.close();
			
			Statement stmt2 = dbManager.getC().createStatement(); //table for n-n relationship between doctors and patients
			String doctors_patients = "CREATE TABLE Doctors_patients "
					   + "(doctor_id    INTEGER  REFERENCES Doctors(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " patient_id  	INTEGER  REFERENCES Patients(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " PRIMARY KEY (doctor_id,patient_id))";
			stmt2.executeUpdate(doctors_patients);
			stmt2.close();
			
			Statement stmtSeq2 = dbManager.getC().createStatement();
			String sqlSeq2 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Doctors', 1)";
			stmtSeq2.executeUpdate(sqlSeq2);
			stmtSeq2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean insertDoctorPatientTable(Integer pat, Integer doct){
		try {
			Statement stmt= dbManager.getC().createStatement();
			String sql = "INSERT INTO Doctors_patients (doctor_id, patient_id)"
					+ " VALUES (" + doct+ ", " + pat + ")";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void dropTable(){
		try{
			Statement stm1 = dbManager.getC().createStatement();
			String drop1 = "DROP TABLE Doctors";
			stm1.executeUpdate(drop1);
			stm1.close();
			
			Statement stm2 = dbManager.getC().createStatement();
			String drop2 = "DROP TABLE Doctors_patients";
			stm2.executeUpdate(drop2);
			stm2.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

