package transplants.db.jdbc;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class SQL_Request {

	private DBManager dbManager;

	public SQL_Request(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}
//
	// C: USED from insert from DBMANGER*/
	public boolean insertRequest(Requested_organ organ) {
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Requested_organs (name, maxWeight, minWeight) " + " VALUES ('" + organ.getName()
					+ "','" + organ.getMaxWeight() + "' , '" + organ.getMinWeight() + "')";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//M: used from dbamanger: assignmentPatientRequest*/
	public boolean insertPatientFK(int idPat, int idReq) {
		try {
			String sql = "UPDATE Requested_organs SET patient_id=? WHERE id=" + idReq;
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, idPat);
			prep.executeUpdate();
			prep.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//used from dbManger(from ui_organ)*/
	public int reqIdByPatIdAndDonOrg (int Patid, String name){
		int id=0;
		try{
		Statement stmt= dbManager.getC().createStatement();
		String sql= "SELECT id FROM Requested_organs WHERE patient_id= '"+ Patid + "' AND name= '"+name+"'";
		ResultSet rs= stmt.executeQuery(sql);
		while (rs.next()){
			id= rs.getInt("id");
		}
		
		stmt.close();
		rs.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return id;
		
	}
	//LO VAMOs a usar??????
	public List<Requested_organ> searchReqOrgan(String name) {
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
	//M: used from dbamanger:update*/
	public boolean updateReqOrgan(Requested_organ organ) {
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
	//M: used from dbmanager: delete*/
	public boolean deleteReqOrgan(Requested_organ organ) {
		try {
			String sql = "DELETE FROM Requested_organs WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, organ.getId());
			prep.executeUpdate();
			prep.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	//M: used by dbamanager: characteristicsOfReqOrg*/
	public List<Requested_organ> characteristicsOfRequests(int idPat) {
		List<Requested_organ> reqs = new ArrayList<Requested_organ>();
		try {
			Statement st = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Requested_organs AS Req JOIN Patients AS Pat "
					+ "ON  Req.patient_id = Pat.id WHERE Req.patient_id = " + idPat;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String nameReqOrgan = rs.getString(2);
				Float maxWeight = rs.getFloat("maxWeight");
				Float minWeight = rs.getFloat("minWeight");
				Requested_organ reqOrgan = new Requested_organ(id, nameReqOrgan, maxWeight, minWeight);
				reqs.add(reqOrgan);
			}

			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqs;
	}

	// C: USED from dbManager idRequestedOrgan(Requested_organ r)*/
	public int getRequestedId(Requested_organ request) {
		int idR = 0;
		Requested_organ ro = new Requested_organ();
		try {
			Statement stm = dbManager.getC().createStatement();
			String sql = "SELECT id FROM Requested_organs WHERE (name LIKE '" + request.getName()
					+ "') AND (maxWeight = " + request.getMaxWeight() + ")" + " AND (minWeight = "
					+ request.getMinWeight() + ")";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				idR = rs.getInt("id");
				ro = new Requested_organ(idR, request.getName(), request.getMaxWeight(), request.getMinWeight());
			}
			rs.close();
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idR;
	}
	
	//used
	public List<Donor> compatiblePatientOrgans(Requested_organ reqOrgan){
		List<Donor> compatibleDonors= new ArrayList<Donor>();
		try{
			Statement stmt1=dbManager.getC().createStatement();
			Statement stmt3= dbManager.getC().createStatement();			
			
				
			String sql3 ="SELECT * FROM AvailableDonors AS ad JOIN organs AS org ON ad.id= org.donor_id " 
						+"WHERE org.name LIKE '%" +reqOrgan.getName()+"%' AND ad.bloodType LIKE '%" +reqOrgan.getPatient().getBloodType()+ 
						"%' AND org.weight <= '"+reqOrgan.getMaxWeight()+"' AND org.weight >= '"+reqOrgan.getMinWeight()+"'";

		
			
			ResultSet rs= stmt3.executeQuery(sql3);
			while (rs.next()) {
				int id = rs.getInt(1);				
				String name = rs.getString(2);				
				Date dob = rs.getDate(3);				
				Float weight = rs.getFloat(4);				
				Float height = rs.getFloat(5);
				String gen = rs.getString(6);
				String deadAlive = rs.getString(7);
				String bloodType = rs.getString(8);
				
				System.out.println("Id:" +id);	
				System.out.println("name:" +name);
				System.out.println("dob: "+dob);
				System.out.println("weight:"+weight);
				System.out.println("height:"+height);
				System.out.println("gen:"+gen);
				System.out.println("deadAlive:"+deadAlive);
				System.out.println("bloodType:"+bloodType);
				
				Donor donorsToShow = new Donor(id,name,dob, weight, height, gen, deadAlive, bloodType);
				compatibleDonors.add(donorsToShow);
			}
			rs.close();
			stmt3.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return compatibleDonors;	
		}

	//used
	public void viewAvailableDonors(){
		try{
				
		Statement stmt2= dbManager.getC().createStatement();
		String sql= "CREATE VIEW AvailableDonors AS SELECT * FROM Donors AS d JOIN Organs AS "
				+ "org ON org.donor_id= d.id WHERE org.id NOT IN (SELECT id FROM organs WHERE "
				+ "requested_id IS NOT NULL)";
		stmt2.executeUpdate(sql);
		
		stmt2.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void createTable() {
		try {

			Statement stmt7 = dbManager.getC().createStatement();
			String requested_organs = "CREATE TABLE Requested_organs " + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					+ " name				TEXT," + " maxWeight 		REAL ," + " minWeight 		REAL,"
					+ " patient_id		INTEGER," + " FOREIGN KEY (patient_id) REFERENCES Patients(id))";
			stmt7.executeUpdate(requested_organs);
			stmt7.close();

			Statement stmtSeq7 = dbManager.getC().createStatement();
			String sqlSeq7 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Requested_organs', 1)";
			stmtSeq7.executeUpdate(sqlSeq7);
			stmtSeq7.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dropTable() {
		try {
			Statement stm = dbManager.getC().createStatement();
			String drop = "DROP TABLE Requested_organs";
			stm.executeUpdate(drop);
			stm.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//used
		public void dropViewAvailableDonors(){
			try{
			Statement stmt1= dbManager.getC().createStatement();
			String sql1= "DROP VIEW AvailableDonors";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
}
