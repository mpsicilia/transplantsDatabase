package transplants.db.jdbc;

import java.sql.*;
import java.util.*;

import transplants.db.pojos.Hospital;

public class SQL_Hospital {

	private DBManager dmanager;

	public SQL_Hospital(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}


	public String insertHospital(Hospital hospital) {
		String sql="";
		try {
			sql = "INSERT INTO Hospitals (name, phone_number, address, city, " + "postcode, country) VALUES ('"
					+ hospital.getName() + "', '" + hospital.getPhone_number() + "'," + " '" + hospital.getAddress()
					+ "', '" + hospital.getCity() + "', '" + hospital.getPostcode() + "', '" + hospital.getCountry()
					+ "');";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	/*public List<Hospital> Search_Hospital(String name) {
		List<Hospital> lookForHospital = new ArrayList<Hospital>();
		try {
			Statement stmt = c.createStatement();
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

	}*/

	public void Create_Table() {
		try {
			Statement stmt1 = dmanager.getC().createStatement();
			String hospitals = "CREATE TABLE Hospitals " 
					+ "(id      		INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name     		TEXT     NOT NULL, " 
					+ " phone_number	TEXT,  "
					+ " address  		TEXT	 NOT NULL, " 
					+ " city 			TEXT," 
					+ " postcode		TEXT,"
					+ " country			TEXT)";
			stmt1.executeUpdate(hospitals);
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
