package transplants.db.jdbc;


import java.sql.Date;

import java.sql.ResultSet;
import java.sql.Statement;

import transplants.db.pojos.Donor;

import transplants.db.pojos.Patient;

public class SQL_Donor {
	
	private DBManager dbManager;

	public SQL_Donor(DBManager dbmanager) {
		this.dbManager = dbmanager;
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
	
	public Donor getDonorOfOrgan (String nameOrg){
		Donor donor = new Donor();
		try{
			Statement stmt  = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Donors AS Don JOIN Organs AS Org ON Don.id = Org.donor_id "
					+ " WHERE Org.name LIKE '" + nameOrg + "'";
			ResultSet rs = stmt.executeQuery(sql);
			Integer id = rs.getInt(1);
			String nameDon = rs.getString(2);
			String birthString = rs.getString("birthDate");
			Date dob = Date.valueOf(birthString);
			Float weight = rs.getFloat(4);
			Float height = rs.getFloat("height");
			String gender = rs.getString("gender");
			String deadAlive = rs.getString("deadAlive");
			String bloodType= rs.getString("bloodType");
			donor = new Donor(id, nameDon, dob, weight, height, gender, deadAlive, bloodType);
		}catch (Exception e){
			e.printStackTrace();
		}
		return donor;
	}
	
	public Donor compatibleDonor (Patient p, Donor d){
		Donor compDonor = new Donor();
		return compDonor;
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
