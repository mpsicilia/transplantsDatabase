package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;

public class SQL_Organ {
	
	private DBManager dbManager;

	public SQL_Organ(DBManager dbmanager) {
		this.dbManager = dbmanager;
		dbManager.connect();
	}
	
	public boolean insertOrgan(Organ organ){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Organs (name, weight, typeOfDonation) "
					+ "VALUES ('"+ organ.getName() + "','" + organ.getWeight() + "' , '" 
					+ organ.getTypeOfDonation() + "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List <Organ> searchOrgan(String name){
		List<Organ> lookForOrgan = new ArrayList<Organ>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Organs WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nameOrgan = rs.getString("name");
				Float weight = rs.getFloat("weight");
				String typeOfDonation= rs.getString("typeOfDonation");
				Organ organToShow = new Organ(id, nameOrgan, weight, typeOfDonation);
				lookForOrgan.add(organToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForOrgan;
	}
	
	public boolean updateOrgan (Organ organ){
		try {
			String sql = "UPDATE Organs SET name=?, weight=?, typeOfDonation =? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, organ.getName());
			prep.setFloat(2, organ.getWeight());
			prep.setString(3, organ.getTypeOfDonation());
			prep.setInt(4, organ.getId());
			prep.executeUpdate();
			prep.close();			
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public boolean deleteOrgan(Organ organ){
		try{
			String sql = "DELETE FROM Organs WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, organ.getId());
			prep.executeUpdate();
			prep.close();
			
			return true;
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public void createTable(){
		try{
			
			Statement stmt6 = dbManager.getC().createStatement();
			String organs = "CREATE TABLE Organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT NOT NULL,"
					   + " weight 			REAL ,"
					   + " typeOfDonation	TEXT,"
					   + " requested_id		INTEGER,"
					   + " donor_id			INTEGER, "
					   + " FOREIGN KEY (requested_id) REFERENCES Requested_organs(id),"
					   + " FOREIGN KEY (donor_id) REFERENCES Donors (id))";
			stmt6.executeUpdate(organs);
			stmt6.close();
			
			Statement stmtSeq6 = dbManager.getC().createStatement();
			String sqlSeq6 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Organs', 1)";
			stmtSeq6.executeUpdate(sqlSeq6);
			stmtSeq6.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dbManager.getC().createStatement();
			String drop = "DROP TABLE Organs";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
