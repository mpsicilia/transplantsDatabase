package transplants.db.jdbc;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;


public class SQL_Organ {
	
	private DBManager dbManager;

	public SQL_Organ(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}
	
	public boolean insertOrgan(Organ organ){
		try{
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Organs (name, weight, typeOfDonation, lifeOfOrgan) "
					+ "VALUES ('"+ organ.getName() + "','" + organ.getWeight() + "' , '" 
					+ organ.getTypeOfDonation() + "','" +organ.getLifeOfOrgan()+"');";
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
				String lifeOfOrg= rs.getString("lifeOfOrgan");
				Date lifeOfOrgan= Date.valueOf(lifeOfOrg);
				
				Organ organToShow = new Organ(id, nameOrgan, weight, typeOfDonation, lifeOfOrgan );
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
			String sql = "UPDATE Organs SET name=?, weight=?, typeOfDonation =?, lifeOfOrgan=? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, organ.getName());
			prep.setFloat(2, organ.getWeight());
			prep.setString(3, organ.getTypeOfDonation());
			prep.setDate(4, organ.getLifeOfOrgan());
			prep.setInt(5, organ.getId());
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
	
	public int getOrganId(Organ org){
		int idO = 0;
		Organ o = new Organ();
		try{
			Statement stm = dbManager.getC().createStatement();
			String sql ="SELECT id FROM Organs WHERE name LIKE '" + org.getName() 
						+ "' AND weight LIKE '" + org.getWeight() 
						+ "' AND typeOfDonation LIKE '" + org.getTypeOfDonation() 
						+ "' AND lifeOfOrgan LIKE '" + org.getLifeOfOrgan() + "'";
			ResultSet rs = stm.executeQuery(sql);
			idO = rs.getInt("id");
			o = new Organ (idO, org.getName(), org.getWeight(), org.getTypeOfDonation(),  org.getLifeOfOrgan());
			
			rs.close();
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return o.getId();
	}
	
	public boolean insertDonorFK (int idDonor, int idOrg){
		try{
			String sql = "UPDATE Organs SET donor_id=? WHERE id=" + idOrg;
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, idDonor);
			prep.executeUpdate();
			prep.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertRequestedFK (int idReq, int idOrg){
		try{
			String sql = "UPDATE Organs SET requested_id=? WHERE id=" + idOrg;
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, idReq);
			prep.executeUpdate();
			prep.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Organ> organOfDonor (int idD){
		List <Organ> orgs = new ArrayList<Organ>();
		try{
			Statement st = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Organs AS Org JOIN Donors AS Don "
					+ "ON  Org.donor_id = Don.id WHERE Org.donor_id = " + idD;
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()){
				Integer id = rs.getInt(1);
				String nameOrgan = rs.getString(2);
				Float weight = rs.getFloat(3);
				String typeDon = rs.getString(4);
				String lifeOfOrg= rs.getString(5);
				Date lifeOfOrgan= Date.valueOf(lifeOfOrg);
				
				Organ organ = new Organ(id, nameOrgan, weight, typeDon, lifeOfOrgan);
				orgs.add(organ);
			}
			
			rs.close();
			st.close();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return orgs;
	}
	
	public List<Patient> CompatibilityTest(Organ organ){
		List<Patient> compatiblePatients= new ArrayList<Patient>();
		try{
			Statement stmt= dbManager.getC().createStatement();//Tendr�a que ser un right join no?
			//COLLATE NOCASE is so that it does not take into account weather it is a capital letter or not
			//Could COLLATE NOCASE be used also for bloodtype... ?
			String sql = "SELECT * FROM Patients JOIN Requested_Organs ON Patients.id = Requested_Organs.patient_id"
					+ "JOIN Organs ON RequestedOrgans.id = Organs.requested_id JOIN Donors ON Organs.donor_id = Donors.id "
					+ "WHERE Requested_Organs.name LIKE '%" + organ.getName() + "%' COLLATE NOCASE "
					+ " AND Patients.bloodType = Donors.bloodType ORDER BY Patient.score";
			ResultSet rs= stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String namePatient = rs.getString(2);
				String birthString = rs.getString(3);
				Date dob = Date.valueOf(birthString);
				Float weight = rs.getFloat(4);
				Float height = rs.getFloat(5);
				String gen = rs.getString(6);
				String patho =  rs.getString(7);
				String bt = rs.getString(8);
				String addString = rs.getString(9);				
				Date doa = Date.valueOf(addString);
				String lifeExp = rs.getString(10);
				Date lifeExpectancy= Date.valueOf(lifeExp);
				
				Patient patientToShow = new Patient(id,namePatient,dob, weight, height, gen, patho, bt,  doa, lifeExpectancy);
				compatiblePatients.add(patientToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return compatiblePatients;	
		}
	public void createTable(){
		try{
			
			Statement stmt6 = dbManager.getC().createStatement();
			String organs = "CREATE TABLE Organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT NOT NULL,"
					   + " weight 			REAL ,"
					   + " typeOfDonation	TEXT,"
					   + " lifeOfOrgan      DATE,"
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
