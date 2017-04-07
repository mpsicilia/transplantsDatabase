package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL_Create {
	
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			//Table creation
			Statement stmt1 = c.createStatement();
			String hospitals = "CREATE TABLE Hospitals "
					   + "(id      		 	INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     		TEXT     NOT NULL, "
					   + " phone_number		TEXT,  "
					   + " address  		TEXT	 NOT NULL, "
					   + " city 			TEXT,"
					   + " postcode			TEXT,"
					   + " country			TEXT)";
			stmt1.executeUpdate(hospitals);
			stmt1.close();
			
			Statement stmt2 = c.createStatement();
			String doctors = "CREATE TABLE Doctors "
					   + "(id       			INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " registration_number 	TEXT,"
					   + " specialization 		TEXT,"
					   + " hospital_id			INTEGER REFERENCES Hospitals(id))";
			stmt2.executeUpdate(doctors);
			stmt2.close();
			
			Statement stmt3 = c.createStatement();
			String patients = "CREATE TABLE Patients "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " birth_date		DATE,"
					   + " weight 			REAL ,"
					   + " height 			REAL,"
					   + " gender			TEXT,"
					   + " pathology 		TEXT,"
					   + " blood_type		TEXT,"
					   + " addition_date 	DATE,"
					   + " life_expectancy 	INTEGER,"
					   + " hospital_id		INTEGER REFERENCES Hospitals (id))";
			stmt3.executeUpdate(patients);
			stmt3.close();
			
			Statement stmt4 = c.createStatement(); //table for n-n relationship between doctors and patients
			String doctors_patients = "CREATE TABLE Doctors_patients "
					   + "(dosctor_id    INTEGER  REFERENCES Doctors(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " patient_id  				INTEGER  REFERENCES Patients(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " PRIMARY KEY (registration_number,patient_id))";
			stmt4.executeUpdate(doctors_patients);
			stmt4.close();
			
			Statement stmt5 = c.createStatement();
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
			
			Statement stmt6 = c.createStatement();
			String organs = "CREATE TABLE Organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " weight 			REAL ,"
					   + " type_of_donation	TEXT,"
					   + " requested_id		INTEGER,"
					   + " donor_id			INTEGER, "
					   + " FOREIGN KEY (requested_id) REFERENCES Requested_organs(id),"
					   + " FOREIGN KEY (donor_id) REFERENCES Donors (id))";
			stmt6.executeUpdate(organs);
			stmt6.close();
			
			Statement stmt7 = c.createStatement();
			String requested_organs = "CREATE TABLE Requested_organs "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " max_weight 		REAL ,"
					   + " min_weight 		REAL,"
					   + " patient_id		INTEGER,"
					   + " FOREIGN KEY (patient_id) REFERENCES Patients(id))";
			stmt7.executeUpdate(requested_organs);
			stmt7.close();
			
			Statement stmt8 = c.createStatement();
			String animal_tissues = "CREATE TABLE Animal_tissues "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name				TEXT,"
					   + " type_of_tissue	TEXT,"
					   + " pathology 		TEXT,"
					   + " time				INTEGER)";
			stmt8.executeUpdate(animal_tissues);
			stmt8.close();
			
			Statement stmt9 = c.createStatement();
			String requested_animals = "CREATE TABLE Requested_Animals "
					   + "(requested_id     INTEGER  REFERENCES Requested_organs(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " animal_id   		INTEGER  REFERENCES Animal_tissues(id) ON UPDATE CASCADE ON DELETE CASCADE,"
					   + " PRIMARY KEY (requested_id,animal_id))";
			stmt9.executeUpdate(requested_animals);
			stmt9.close();
			System.out.println("Tables created.");

			
			//Initialize primary keys
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Hospitals', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Doctors', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Patients', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Donors', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Organs', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Requested_organs', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('Animal_tissues', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			//Close connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
