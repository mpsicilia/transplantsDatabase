package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Organ;
import transplants.db.pojos.Requested_organ;

public class SQL_Request {


	private DBManager dbManager;

	public SQL_Request(DBManager dbmanager) {
		this.dbManager = dbmanager;
		dbManager.connect();
	}
	
	public boolean insertRequest(Requested_organ organ){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Requested_organs (name, maxWeight, minWeight) "
					+ "VALUES ('"+ organ.getName() + "','" + organ.getMaxWeight() + "' , '" 
					+ organ.getMinWeight() + "');";
			stmt.executeUpdate(sql);			
			stmt.close();
			return true;
					
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertPatientFK (int idPat){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Requested_organs (patient_id) VALUES ('" + idPat + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List <Requested_organ> searchReqOrgan(String name){
		List<Requested_organ> lookForReqOrgan = new ArrayList<Requested_organ>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Requested_organs WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nameReqOrgan = rs.getString("name");
				Float maxWeight = rs.getFloat("maxWeight");
				Float minWeight = rs.getFloat("minWeight");
				Requested_organ reqOrganToShow = new Requested_organ(id, nameReqOrgan, maxWeight, minWeight);
				lookForReqOrgan.add(reqOrganToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForReqOrgan;
	}
	
	public boolean updateReqOrgan (Requested_organ organ){
		try {
			String sql = "UPDATE Requested_organs SET name=?, maxWeight=?, minWeight =? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, organ.getName());
			prep.setFloat(2, organ.getMaxWeight());
			prep.setFloat(3, organ.getMinWeight());
			prep.setInt(4, organ.getId());
			prep.executeUpdate();
			prep.close();			
		return true;
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}
	
	public boolean deleteReqOrgan(Requested_organ organ){
		try{
			String sql = "DELETE FROM Requested_organs WHERE id=?";
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
	
	public List<Requested_organ> requestedOfPatient (int idPat){
		List <Requested_organ> reqs = new ArrayList<Requested_organ>();
		try{
			Statement st = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Requested_organs AS Req JOIN Patients AS Pat "
					+ "ON  Req.patient_id = Pat.id WHERE Req.patient_id = " + idPat ;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
				Integer id = rs.getInt("id");
				String nameReqOrgan = rs.getString("name");
				Float maxWeight = rs.getFloat("maxWeight");
				Float minWeight = rs.getFloat("minWeight");
				Requested_organ reqOrgan = new Requested_organ(id, nameReqOrgan, maxWeight, minWeight);
				reqs.add(reqOrgan);
			}
			
			rs.close();
			st.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return reqs;
	}
	
	public void createTable (){
		try{
			
			Statement stmt7 = dbManager.getC().createStatement();
			String requested_organs = "CREATE TABLE Requested_organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " maxWeight 		REAL ,"
					   + " minWeight 		REAL,"
					   + " patient_id		INTEGER,"
					   + " FOREIGN KEY (patient_id) REFERENCES Patients(id))";
			stmt7.executeUpdate(requested_organs);
			stmt7.close();
			
			Statement stmtSeq7 = dbManager.getC().createStatement();
			String sqlSeq7 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Requested_organs', 1)";
			stmtSeq7.executeUpdate(sqlSeq7);
			stmtSeq7.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dbManager.getC().createStatement();
			String drop = "DROP TABLE Requested_organs";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
