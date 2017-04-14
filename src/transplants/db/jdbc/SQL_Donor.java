package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Donor {
	
	private DBManager dmanager;

	public SQL_Donor(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}

	
	public void createTable(){
		try{
			
			Statement stmt5 = dmanager.getC().createStatement();
			String donors = "CREATE TABLE Donors "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " birth_date		DATE,"
					   + " weight 			REAL ,"
					   + " height 			REAL,"
					   + " gender			TEXT,"
					   + " dead_alive		TEXT,"
					   + " blood_type		TEXT)";
			stmt5.executeUpdate(donors);
			stmt5.close();
			
			Statement stmtSeq5 = dmanager.getC().createStatement();
			String sqlSeq5 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Donors', 1)";
			stmtSeq5.executeUpdate(sqlSeq5);
			stmtSeq5.close();
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dmanager.getC().createStatement();
			String drop = "DROP TABLE Donors";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
