package transplants.db.jdbc;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class SQL_Doctor {
	
	private DBManager dbManager;
	
	public SQL_Doctor(DBManager dbmanager){
		this.dbManager= dbmanager;
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
			String sql = "SELECT id FROM Doctors "
					+ "WHERE name LIKE '%" + doctor.getNameOfDoctor() + "%' AND registrationNumber"
							+ " LIKE '" + doctor.getRegistrationNumber()+ "' AND specialization "
							+ "LIKE '" + doctor.getSpecialization()+ "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				doctorToShow = new Doctor(id, doctor.getNameOfDoctor(),
						doctor.getRegistrationNumber(),doctor.getSpecialization());
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
	
	public List <Doctor> doctorsWorkingInHospital (String nameHosp){
		List<Doctor> docsInHosp = new ArrayList<Doctor>();
		try{
			Statement stm = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Doctors AS Doc JOIN HospitalsDoctors AS HospDoc ON Doc.id = HospDoc.doctor_id "
					+ "JOIN Hospitals AS Hosp ON HospDoc.hospital_id = Hosp.id WHERE Hosp.name LIKE '" + nameHosp + "'";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String nameDoctor = rs.getString(2);
				String regNumber = rs.getString("registrationNumber");
				String specializ = rs.getString("specialization");
				Doctor doctor = new Doctor(id, nameDoctor,regNumber,specializ);
				docsInHosp.add(doctor);
			}
			rs.close();
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return docsInHosp;
	}

	public List<Doctor> doctorsAttendingPatient (String patName){
		List<Doctor> patDoctors = new ArrayList<Doctor>();
		try{
			Statement stmt = dbManager.getC().createStatement();
			String searchSql = "SELECT * FROM Doctors "
					+ "AS Doct JOIN Doctors_patients AS DoctPatients ON "
					+ "Doct.id=DoctPatients.doctor_id JOIN Patients AS Pat ON "
					+ "Pat.id=DoctPatients.patient_id WHERE Pat.name LIKE '%" + patName + "%'";
			ResultSet rs = stmt.executeQuery(searchSql);
			
			while (rs.next()) {
				Integer id=rs.getInt(1);
				String name = rs.getString(2);
				String regNumber= rs.getString(3);
				String specializ=rs.getString(4);
				Doctor doctor = new Doctor(id, name, regNumber,specializ);
				patDoctors.add(doctor);	
			}
			rs.close();
			stmt.close();
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
	public boolean insertDoctorPatientTable(Integer patId, Integer doctId){
		try {
			Statement stmt= dbManager.getC().createStatement();
			String sql = "INSERT INTO Doctors_patients (doctor_id, patient_id)"
					+ " VALUES (" + doctId+ ", " + patId + ")";
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
			
			/*Statement stm2 = dbManager.getC().createStatement();
			String drop2 = "DROP TABLE Doctors_patients";
			stm2.executeUpdate(drop2);
			stm2.close();*/
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

