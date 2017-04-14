package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Request {


	private DBManager dmanager;

	public SQL_Request(DBManager dbmanager) {
		this.dmanager = dbmanager;
		dmanager.connect();
	}
	
	
	public void createTable (){
		try{
			
			Statement stmt7 = dmanager.getC().createStatement();
			String requested_organs = "CREATE TABLE Requested_organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " max_weight 		REAL ,"
					   + " min_weight 		REAL,"
					   + " patient_id		INTEGER,"
					   + " FOREIGN KEY (patient_id) REFERENCES Patients(id))";
			stmt7.executeUpdate(requested_organs);
			stmt7.close();
			
			Statement stmtSeq7 = dmanager.getC().createStatement();
			String sqlSeq7 = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Requested_organs', 1)";
			stmtSeq7.executeUpdate(sqlSeq7);
			stmtSeq7.close();
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try{
			Statement stm = dmanager.getC().createStatement();
			String drop = "DROP TABLE Requested_organs";
			stm.executeUpdate(drop);
			stm.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
