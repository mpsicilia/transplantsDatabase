package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class SQL_Donor {
	
	private DBManager dbManager;

	public SQL_Donor(DBManager dbmanager) {
		this.dbManager = dbmanager;
		dbManager.connect();
	}
	
	public boolean insertDonor (Donor donor){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Donors (name, birthDate, weight, height, gender, deadAlive, bloodType) "
					+ "VALUES ('"+ donor.getName() + "','" + donor.getBirthDate() + "' , '" + donor.getWeight() +
					"' , '" + donor.getHeight() + "' , '" + donor.getGender() + "' , '" + donor.getDeadOrAlive()+ "' , '" +
					donor.getBloodType() + "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List <Donor> searchDonor(String name){
		List<Donor> lookForDonor = new ArrayList<Donor>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Donors WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nameDon = rs.getString("name");
				String birthString = rs.getString("birthDate");
				Date dob = Date.valueOf(birthString);
				Float weight = rs.getFloat("weight");
				Float height = rs.getFloat("height");
				String gender = rs.getString("gender");
				String deadAlive = rs.getString("deadAlive");
				String bloodType= rs.getString("bloodType");
				Donor donorToShow = new Donor(id, nameDon, dob, weight, height, gender, deadAlive, bloodType);
				lookForDonor.add(donorToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForDonor;
	}
	
	public boolean updateDonor (Donor donor){
		try {
			String sql = "UPDATE Donors SET name=?, birhDate=?, weight=?,"
					+ "height =?, gender=?, deadAlive=?, bloodType=? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, donor.getName());
			prep.setDate (2, donor.getBirthDate());
			prep.setFloat(3, donor.getWeight());
			prep.setFloat(4, donor.getHeight());
			prep.setString(5, donor.getGender());
			prep.setString(6, donor.getDeadOrAlive());
			prep.setString(7, donor.getBloodType());
			prep.setInt(8, donor.getId());
			prep.executeUpdate();
			prep.close();			
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public boolean deleteDonor(Donor donor){
		try{
			String sql = "DELETE FROM Donors WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, donor.getId());
			prep.executeUpdate();
			prep.close();
			
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	//2. See the organ that is going to donate
	public String OrganDonating (String donorName){
		return "";
	}
	
	public int getDonorID (Donor d){
		Donor donor =  new Donor();
		try{
			Statement stm = dbManager.getC().createStatement();
			String sql ="SELECT id FROM Donors "
					+ "WHERE (name LIKE '" + d.getName() + "') "
							+ "AND (weight = " + d.getWeight() + ")"
							+ " AND (height = " + d.getHeight() + ") "
							+ "AND (gender LIKE '" + d.getGender() + "')"
							+ "AND (deadAlive LIKE '" + d.getDeadOrAlive() + "') "
							+ "AND (bloodType LIKE '" + d.getBloodType() + "')";
			ResultSet rs = stm.executeQuery(sql);
			int idD = rs.getInt("id");
			donor = new Donor (idD, d.getName(), d.getBirthDate(), d.getWeight(), d.getHeight(), d.getGender(), d.getDeadOrAlive(), d.getBloodType());
			
			rs.close();
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return donor.getId();
	}
	
	
	/*METHODS RELATED WITH THE TABLES*/
	public void createTable(){
		try{
			
			Statement stmt5 = dbManager.getC().createStatement();
			String donors = "CREATE TABLE Donors "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name             TEXT NOT NULL,"
					   + " birthDate		DATE NOT NULL,"
					   + " weight 			REAL ,"
					   + " height 			REAL,"
					   + " gender			TEXT,"
					   + " deadAlive		TEXT,"
					   + " bloodType		TEXT)";
			stmt5.executeUpdate(donors);
			stmt5.close();
			
			Statement stmtSeq5 = dbManager.getC().createStatement();
			String sqlSeq5 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Donors', 1)";
			stmtSeq5.executeUpdate(sqlSeq5);
			stmtSeq5.close();
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}	
	public void dropTable() {
		try{
			Statement stm = dbManager.getC().createStatement();
			String drop = "DROP TABLE Donors";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
