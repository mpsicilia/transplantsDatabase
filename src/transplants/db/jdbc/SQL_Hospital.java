package transplants.db.jdbc;

import java.sql.*;
import java.util.*;

import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class SQL_Hospital {

	private DBManager dmanager;

	public SQL_Hospital(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}

	public boolean insertHospital(Hospital hospital) {
		
		try {
			Statement stmt = dmanager.getC().createStatement();
			String sql = "INSERT INTO Hospitals (name, phone_number, address, city, " + "postcode, country) VALUES ('"
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
				String phone_number1 = rs.getString("phone_number");
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
	
	//method that tell us given a specific doctor, in which hospital he works
	public String searchDoctorInHospital (String doctorName){
		String searchSql = "";
		try{
			Statement stmt = dmanager.getC().createStatement();
			searchSql = "SELECT Hosp.name, Doct.name FROM Hospitals "
					+ "AS Hosp JOIN HospitalsDoctors AS HospDocts ON Hosp.id=HospDocts.hospital_id "
					+ "RIGHT JOIN Doctors AS Doct ON Doct.id=HospDocts.doctor_id "
					+ "WHERE Doct.name LIKE '" + doctorName + "'";
			stmt.executeUpdate(searchSql);
			stmt.close();
			return searchSql;
		}catch (Exception e){
			e.printStackTrace();
		}
		return searchSql;
	}
	
	public boolean updateHospital (Hospital hosp){		
		try {
			String sql = "UPDATE Hospitals SET name=?, phone_number=?, address=?,"
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
			prep.executeUpdate(sql);
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
				String phone_number1 = rs.getString("phone_number");
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

	public void createTable() {
		try {
			Statement stmt1 = dmanager.getC().createStatement();
			String hospitals = "CREATE TABLE Hospitals " 
					+ "(id      		INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT     NOT NULL, " 
					+ " phone_number	TEXT,  "
					+ " address  		TEXT	 NOT NULL, " 
					+ " city 			TEXT," 
					+ " postcode		TEXT,"
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
