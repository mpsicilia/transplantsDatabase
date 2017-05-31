package transplants.db.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;

public class SQL_Patient {

	private DBManager dbManager;

	public SQL_Patient(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}
	//SEGUIR Aï¿½ADIENDO SCORE 
	
	public boolean insertPatient (Patient p){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Patients (name, birthDate, weight, height, gender, pathology, bloodType, "
					+ "additionDate, lifeExpectancy, score) VALUES ('"+ p.getName() + "','" + p.getBirthDate() + "' , '" + p.getWeight() +
					"' , '" + p.getHeight() + "' , '" + p.getGender() + "' , '" + p.getPathology() + "' , '" +
					p.getBloodType() + "' , '" + p.getAdditionDate() + "' , '" + p.getLifeExpectancy() + "' , '" +p.getScore()+ "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	//public List<Patient> .. seleccionar los pacientes con el nombre del organo y el bloodtype del organo
	
	
	
	
	public List<Patient> searchPatient(String namePat) {
		List<Patient> lookForPatient = new ArrayList<Patient>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Patients WHERE name LIKE '%" + namePat + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String namePatient = rs.getString("name");
				Date dob = rs.getDate("birthDate");				
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String gen = rs.getString("gender");
				String patho =  rs.getString("pathology");
				String bt = rs.getString("bloodType");
				Date doa = rs.getDate("additionDate");				
				Date lifeExpectancy= rs.getDate("lifeExpectancy");				
				
				
				Patient patientToShow = new Patient(id,namePatient,dob, weight, height, gen, patho, bt, doa, lifeExpectancy);
				lookForPatient.add(patientToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForPatient;
	}
	
	public Patient searchPatientById (Integer idPat) {
		Patient patientToShow= new Patient();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Patients WHERE id LIKE '%" + idPat + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			//We don´t do the while because we asume that there is only one patient with that id
				int id = rs.getInt("id");
				String namePatient = rs.getString("name");
				Date dob = rs.getDate("birthDate");				
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String gen = rs.getString("gender");
				String patho =  rs.getString("pathology");
				String bt = rs.getString("bloodType");
				Date doa = rs.getDate("additionDate");				
				Date lifeExpectancy= rs.getDate("lifeExpectancy");				
				
				
				patientToShow = new Patient(id,namePatient,dob, weight, height, gen, patho, bt, doa, lifeExpectancy);
				
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return patientToShow;
	
	}
	
	

	
	
	public boolean updatePatient (Patient p){
		try{
			String sql = "UPDATE Patients SET name=?, birthDate=?, weight=?, height=?, gender=?, pathology=?,"
					+ "bloodType=?, additionDate=?, lifeExpectancy=?, score=? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, p.getName());
			prep.setDate(2, p.getBirthDate());
			prep.setFloat(3, p.getWeight());
			prep.setFloat(4, p.getHeight());
			prep.setString(5, p.getGender());
			prep.setString(6, p.getPathology());
			prep.setString(7, p.getBloodType());
			prep.setDate(8, p.getAdditionDate());
			prep.setDate(9, p.getLifeExpectancy());
			prep.setInt(10, p.getId());			
			prep.executeUpdate();
			prep.close();			
		return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deletePatient(Patient p){
		try{
			String sql = "DELETE FROM Patients WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, p.getId());
			prep.executeUpdate();
			prep.close();			
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	//given a requested organ returns the patient that needs it
	public String patientRequested (int idReq){
		String nameP = "";
		try{
			Statement st = dbManager.getC().createStatement();
			String sql = "SELECT name FROM Patients AS Pat JOIN Requested_organs AS Req "
					+ "ON Pat.id = Req.patient_id WHERE Req.id = " + idReq ;
			ResultSet rs = st.executeQuery(sql);
			nameP = rs.getString("name");
			
			rs.close();
			st.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return nameP;
	}
	//public SQL_Request 
	public boolean insertHospitalFK(Integer patID, Integer hospID){
		try{
			String sql = "UPDATE Patients SET hospital_id=? WHERE id=" + patID;
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, hospID);
			prep.executeUpdate();
			prep.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Integer getPatientID (Patient p){
		
		Patient patient =  new Patient();
		try{
			Statement stm = dbManager.getC().createStatement();
			String sql ="SELECT id FROM Patients WHERE (name LIKE '" + p.getName() + "') AND (weight = " + p.getWeight() + ")"
					+ " AND (height = " + p.getHeight() + ") AND (gender LIKE '" + p.getGender() + "') AND "
							+ "(pathology LIKE '" + p.getPathology() + "') AND (bloodType LIKE '" + p.getBloodType() + "')"
									+ "AND (lifeExpectancy = " + p.getLifeExpectancy() + ")";
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
			int idP = rs.getInt("id");
			patient = new Patient ( idP, p.getName(), p.getBirthDate(), p.getWeight(), p.getHeight(), p.getGender(), p.getPathology(), p.getBloodType(), p.getAdditionDate(), p.getLifeExpectancy());
			System.out.println("ID "+idP);
			}
			rs.close();
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return patient.getId();
	}
	
	public List<Patient> selectAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Patients";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date dob = rs.getDate("birthDate");
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String gen = rs.getString("gender");
				String patho =  rs.getString("pathology");
				String bt = rs.getString("bloodType");
				Date doa = rs.getDate("additionDate");				
				Date lifeExpectancy= rs.getDate("lifeExpectancy");
				Long score= rs.getLong("score");
				Patient pat = new Patient (id, name, dob, weight, height, gen, patho, bt, doa,lifeExpectancy,score);
				pat.generateScore();
				patients.add(pat);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return patients;
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
					   + " lifeExpectancy 	DATE,"
					   + " score            REAL,"
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
