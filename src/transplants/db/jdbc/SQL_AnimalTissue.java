package transplants.db.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import transplants.db.pojos.Animal_tissue;

public class SQL_AnimalTissue {

	private DBManager dbManager;

	public SQL_AnimalTissue(DBManager dbmanager) {
		this.dbManager = dbmanager;
	}

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

	public List<Animal_tissue> searchAnimalTissue(String name) {
		List<Animal_tissue> lookForAnimalT = new ArrayList<Animal_tissue>();
		try {
			Statement stmt = dbManager.getC().createStatement();
			String sql = "SELECT * FROM Animal_tissues WHERE name LIKE '%" + name + "%'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nameAnimalT = rs.getString("name");
				String typeOfTissue = rs.getString("typeOfTissue");
				String pathology = rs.getString("pathology");
				String lifeExpTis= rs.getString("lifeExpTissue");
				Date lifeExpTissue= Date.valueOf(lifeExpTis);
				Animal_tissue animalTiToShow = new Animal_tissue(id, nameAnimalT, typeOfTissue, pathology, lifeExpTissue);
				lookForAnimalT.add(animalTiToShow);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return lookForAnimalT;
	}

	public boolean updateAnimalTissue(Animal_tissue animalTi) {
		try {
			String sql = "UPDATE Animal_tissue SET name=?, typeOfTissue=?, pathology=?, time=? WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setString(1, animalTi.getName());
			prep.setString(2, animalTi.getTypeOfTissue());
			prep.setString(3, animalTi.getPathology());
			prep.setDate(4, animalTi.getLifeExpTissue());
			prep.setInt(5, animalTi.getId());
			prep.executeUpdate();
			prep.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	public boolean deleteAnimalTissue(Animal_tissue animalT) {
		try {
			String sql = "DELETE FROM Animal_tissue WHERE id=?";
			PreparedStatement prep = dbManager.getC().prepareStatement(sql);
			prep.setInt(1, animalT.getId());
			prep.executeUpdate();
			prep.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean insertRequestedAnimal(Integer id_requested, Integer id_animal) {
		try {
			Statement st = dbManager.getC().createStatement();
			String sql = "INSERT INTO RequestedOrgan_AnimalTissues (requested_id, animal_id) " + "VALUES ("
					+ id_requested + ", " + id_animal + ");";
			st.executeUpdate(sql);
			st.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
			// Should we initialized the second table?
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
