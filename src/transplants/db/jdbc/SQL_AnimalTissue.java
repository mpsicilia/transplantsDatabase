package transplants.db.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import transplants.db.pojos.Animal_tissue;

public class SQL_AnimalTissue {

	private DBManager dbManager;

	public SQL_AnimalTissue(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}
	//C: USED in order to insert an animaltissue
	public boolean insertAnimalTissue(Animal_tissue animalT) {
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "INSERT INTO Animal_tissues (name, typeOfTissue, pathology, lifeExpTissue) " + "VALUES ('"
					+ animalT.getName() + "', '" + animalT.getTypeOfTissue() + "' , '" + animalT.getPathology()
					+ "' , '" + animalT.getLifeExpTissue() + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//used to update the animal tissue
	public boolean updateAnimalTissue(Animal_tissue animalTi) {
		try {
			String sql = "UPDATE Animal_tissues SET name=?, typeOfTissue=?, pathology=?, lifeExpTissue=? WHERE id=? ";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, animalTi.getName());
			prep.setString(2, animalTi.getTypeOfTissue());
			prep.setString(3, animalTi.getPathology());
			prep.setDate(4, animalTi.getLifeExpTissue());
			prep.setInt(5, animalTi.getId());
			prep.executeUpdate();
			prep.close();//
			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	//In order to link a request organ with an animal
	public boolean insertRequestedAnimal(Integer id_requested, Integer id_animal) {
		try {
			Statement st = dbManager.getC().createStatement();
			String sql = "INSERT INTO RequestedOrgan_AnimalsTissues (requested_id, animal_id) "
					+ "VALUES (" + id_requested + ", " + id_animal + ");";
			st.executeUpdate(sql);
			st.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//C: USED in order to get the id of an animal Tissue
	public Integer getAnimalId(Animal_tissue animalT){
		Animal_tissue animalToShow= new Animal_tissue();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT id FROM Animal_tissues "
					+ "WHERE name LIKE '%" + animalT.getName() + "%' AND typeOfTissue"
					+ " LIKE '" + animalT.getTypeOfTissue()+ "' AND pathology "
					+ "LIKE '" + animalT.getPathology()+ "' AND lifeExpTissue "
					+ "LIKE '" + animalT.getLifeExpTissue() + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				animalToShow = new Animal_tissue(id, animalT.getName(),animalT.getTypeOfTissue(),
						                        animalT.getPathology(), animalT.getLifeExpTissue());
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return animalToShow.getId();
	}
	//M: used in order to update animal/with requested
	public Animal_tissue getAnimalOfRequestedOrgan (Integer idReq){
		Animal_tissue animal = new Animal_tissue(); 
		try{
			Statement stm = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Animal_tissues AS at JOIN RequestedOrgan_AnimalsTissues AS roat "
					+ " ON at.id = roat.animal_id JOIN Requested_organs AS ro ON roat.requested_id = ro.id "
					+ " WHERE ro.id = " + idReq + "";
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()){
				Integer id = rs.getInt(1);
				String nameAnimalT = rs.getString(2);
				String typeOfTissue = rs.getString(3);
				String pathology = rs.getString(4);
				Date lifeExpTissue= rs.getDate("lifeExpTissue");
			animal = new Animal_tissue(id, nameAnimalT, typeOfTissue, pathology, lifeExpTissue);
			System.out.println("ANIMAL"+animal);
			
			}
			rs.close();
			stm.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return animal;
	}
	
	public void createTable() {
		try {

			Statement stmt8 = dbManager.getC().createStatement();
			String animal_tissues = "CREATE TABLE Animal_tissues "
					+ "(id               INTEGER  PRIMARY KEY AUTOINCREMENT," + " name				TEXT,"
					+ " typeOfTissue	    TEXT," + " pathology 		TEXT," + " lifeExpTissue    DATE)";
			stmt8.executeUpdate(animal_tissues);
			stmt8.close();

			Statement stmt9 = dbManager.getC().createStatement();
			String requested_animals = "CREATE TABLE RequestedOrgan_AnimalsTissues "
					+ "(requested_id     INTEGER  REFERENCES Requested_organs(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " animal_id   		INTEGER  REFERENCES Animal_tissues(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					+ " PRIMARY KEY (requested_id,animal_id))";
			stmt9.executeUpdate(requested_animals);
			stmt9.close();
			
			Statement stmtSeq8 = dbManager.getC().createStatement();
			String sqlSeq8 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Animal_tissues', 1)";
			stmtSeq8.executeUpdate(sqlSeq8);
			stmtSeq8.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dropTable() {
		try {
			Statement stm1 = dbManager.getC().createStatement();
			String drop1 = "DROP TABLE Animal_tissues";
			stm1.executeUpdate(drop1);
			stm1.close();

			Statement stm2 = dbManager.getC().createStatement();
			String drop2 = "DROP TABLE RequestedOrgan_AnimalsTissues";
			stm2.executeUpdate(drop2);
			stm2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
