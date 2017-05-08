package transplants.db.jdbc;

import java.sql.*;
import java.util.*;
import java.sql.PreparedStatement;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;

public class SQL_Hospital {

	private DBManager dmanager;

	public SQL_Hospital(DBManager dbmanager) {
		this.dmanager = dbmanager;
	}

	public boolean insertHospital(Hospital hospital) {		
		try {
			Statement stmt = dmanager.getC().createStatement();
			String sql = "INSERT INTO Hospitals (name, phoneNumber, address, city, postcode, country) VALUES ('"
					+ hospital.getName() + "', '" + hospital.getPhone_number() + "'," + " '" + hospital.getAddress()
					+ "', '" + hospital.getCity() + "', '" + hospital.getPostcode() + "', '" + hospital.getCountry()
					+ "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public List<Hospital> searchHospital(String name) {
		List<Hospital> lookForHospital = new ArrayList<Hospital>();
		try {
			Statement stmt = dmanager.getC().createStatement();
			String sql = "SELECT * FROM Hospitals WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				String phone_number1 = rs.getString("phoneNumber");
				String address1 = rs.getString("address");
				String city1 = rs.getString("city");
				String postcode1 = rs.getString("postcode");
				String country1 = rs.getString("country");
				Hospital hospitalToShow = new Hospital(id, name1, phone_number1, address1, city1, postcode1, country1);
				lookForHospital.add(hospitalToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForHospital;
	}
	//new method that searches for a hospital with an id-->to use in UIDOCTOR
	//NOSE SI ESTA BIEN EL SQL escrito
	public String Hospitalofdoctor (Integer idhosp){
		String hosp = "";
		try{
			Statement stmt = dmanager.getC().createStatement();
			String searchSql = "SELECT name FROM Hospitals "
					+ "WHERE id = "+ idhosp; 
			ResultSet rs = stmt.executeQuery(searchSql);
			
			while (rs.next()) {
				hosp = rs.getString("name");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		} 
		return hosp; //devuelve el nombre del hospital
	}
	
	
	//method that tell us given a specific doctor, in which hospital he works
	public List<Hospital> searchHospitalsOfDoctor (String doctorName){
		List<Hospital> hosps = new ArrayList<Hospital>();
		try{
			Statement stmt = dmanager.getC().createStatement();
			String searchSql = "SELECT * FROM Hospitals "
					+ "AS Hosp JOIN HospitalsDoctors AS HospDocts ON Hosp.id=HospDocts.hospital_id "
					+ "JOIN Doctors AS Doct ON Doct.id=HospDocts.doctor_id "
					+ "WHERE Doct.name LIKE '%" + doctorName + "%'";
			ResultSet rs = stmt.executeQuery(searchSql);
			while (rs.next()){
				 Integer id = rs.getInt(1);
				 String nameHosp = rs.getString(2);
				 String phone = rs.getString(3);
				 String address = rs.getString(4);
				 String city = rs.getString(5);
				 String pc = rs.getString(6);
				 String country =  rs.getString(6);
				 Hospital h = new Hospital(id, nameHosp, phone, address, city, pc, country);
				 hosps.add(h);
			}
			
			rs.close();
			stmt.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return hosps;
	}
	
	//given a patient name is going to return the hospital in which the patient is
	public String hospitalOfPatient (String patName){
		String hosp = "";
		try{
			Statement stmt = dmanager.getC().createStatement();
			String searchSql = "SELECT * FROM Hospitals "
					+ "AS Hosp JOIN Patients AS Pat ON Hosp.id=Pat.hospital_id "
					+ "WHERE Pat.name LIKE '%" + patName + "%'";
			ResultSet rs = stmt.executeQuery(searchSql);
			
			while (rs.next()) {
				hosp = rs.getString(2);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		} 
		return hosp;
	}
	
	public boolean updateHospital (Hospital hosp){		
		try {
			String sql = "UPDATE Hospitals SET name=?, phoneNumber=?, address=?,"
					+ "city =?, postcode=?, country=? WHERE id=?";
			PreparedStatement prep = dmanager.getC().prepareStatement(sql);
			prep.setString(1, hosp.getName());
			prep.setString(2, hosp.getPhone_number());
			prep.setString(3, hosp.getAddress());
			prep.setString(4, hosp.getCity());
			prep.setString(5, hosp.getPostcode());
			prep.setString(6, hosp.getCountry());
			prep.setInt(7, hosp.getId());
			prep.executeUpdate();
			prep.close();
			
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public boolean deleteHospital (Hospital hospital){
		try{
			String sql = "DELETE FROM Hospitals WHERE id=?";
			PreparedStatement prep = dmanager.getC().prepareStatement(sql);
			prep.setInt(1, hospital.getId());
			prep.executeUpdate();
			prep.close();
			
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Hospital> selectAllHospitals() {
		List<Hospital> lookForHospital = new ArrayList<Hospital>();
		try {
			Statement stmt = dmanager.getC().createStatement();
			String sql = "SELECT * FROM Hospitals";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name1 = rs.getString("name");
				String phone_number1 = rs.getString("phoneNumber");
				String address1 = rs.getString("address");
				String city1 = rs.getString("city");
				String postcode1 = rs.getString("postcode");
				String country1 = rs.getString("country");
				Hospital hospitalToShow = new Hospital(id, name1, phone_number1, address1, city1, postcode1, country1);
				lookForHospital.add(hospitalToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForHospital;
	}
	
	public boolean insertHospitalsDoctors (Integer hospitalId, Integer doctorId){
		try{
			Statement st = dmanager.getC().createStatement();
			String sql = "INSERT INTO HospitalsDoctors (doctor_id, hospital_id) "
					+ "VALUES (" + doctorId + " , " + hospitalId + ");";
			st.executeUpdate(sql);
			st.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public void createTable() {
		try {
			Statement stmt1 = dmanager.getC().createStatement();
			String hospitals = "CREATE TABLE Hospitals " 
					+ "(id      		INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT     NOT NULL, " 
					+ " phoneNumber	    TEXT,              "
					+ " address  		TEXT	 NOT NULL, " 
					+ " city 			TEXT,              " 
					+ " postcode		TEXT,              "
					+ " country			TEXT     NOT NULL)";
			stmt1.executeUpdate(hospitals);
			stmt1.close();
			
			Statement stmt2 = dmanager.getC().createStatement();
			String hospitalsDoctors = "CREATE TABLE HospitalsDoctors " 
					+ "(doctor_id   INTEGER  REFERENCES Doctors(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ "hospital_id	INTEGER  REFERENCES Hospitals(id) ON UPDATE CASCADE ON DELETE CASCADE,"
				    + "PRIMARY KEY (doctor_id,hospital_id))";
			stmt2.executeUpdate(hospitalsDoctors);
			stmt2.close();
			
			//initialize primary key
			Statement stmtSeq1 = dmanager.getC().createStatement();
			String sqlSeq1 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Hospitals', 1)";
			stmtSeq1.executeUpdate(sqlSeq1);
			stmtSeq1.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm1 = dmanager.getC().createStatement();
			String drop1 = "DROP TABLE Hospitals";
			stm1.executeUpdate(drop1);
			stm1.close();
			
			Statement stm2 = dmanager.getC().createStatement();
			String drop2 = "DROP TABLE HospitalsDoctors";
			stm2.executeUpdate(drop2);
			stm2.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
