package transplants.db.jdbc;

import java.sql.Statement;
//88
public class SQL_Donor {
	
	private DBManager dbManager;

	public SQL_Donor(DBManager dbmanager) {
		this.dbManager = dbmanager;
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
