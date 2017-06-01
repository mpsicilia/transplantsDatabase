package transplants.db.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Patient;

public class SQL_Patient {

	private DBManager dbManager;

	public SQL_Patient(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}

	//NOT USED yet....
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
	//LO tenemos en JPA, tambien lo queremos en JDBC??????
	
	/*public Integer getPatientID (Patient p){
		
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
	}*/
	
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
