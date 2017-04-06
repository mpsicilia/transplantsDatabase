package transplants.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class DBManager implements DBManagerInterface{
	private Connection c;
	private SQL_Hospital hosp;
	private SQL_Doctor doct;
	/*private SQL_Patient pat;
	private SQL_AnimalTissue animalT;
	private SQL_Organ org;
	private SQL_Donor don;
	private SQL_Request req;*/

	
	public DBManager() {
		super();
	}

	public Connection getC() {
		return c;
	}

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");//to create a connection
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//specific directory(.: actual current directory)
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			//hosp=new SQL_Hospital();
			//doct=new SQL_Doctor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void disconnect() {
		try {
			c.close();//to close the connection
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean insert(Object obj) {
		String sql="";		
		try{
			hosp = new SQL_Hospital(this); //create connection
			Statement stmt = c.createStatement();//not parameter 
	
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;			
				sql=hosp.insertHospital(hospital);
				
			}
			if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				sql=doct.insertDoctor(doctor);
			}
			
			stmt.executeUpdate(sql);			
			stmt.close();			
			return true;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Object> search(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
