package transplants.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.omg.CORBA.Request;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;

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
			
		try{
			hosp = new SQL_Hospital(this); //create connection
				
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;			
				return hosp.insertHospital(hospital);
			}
			
			if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				return doct.insertDoctor(doctor);
			}
						
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Hospital> searchHosp(String name) {	
		
		try{
			hosp = new SQL_Hospital(this); //create connection
			List<Hospital> hospList= hosp.searchHospital(name);
			return hospList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}
		
	@Override
	public List<Doctor> searchDoctor(String name) {
		try{
			doct = new SQL_Doctor(this); //create connection
			List<Doctor> doctList= doct.searchDoctor(name);
			return doctList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Animal_tissue> searchAnimalT(String name) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Donor> searchDonor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organ> searchOrgan(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatient(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Request> searchRequest(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean update(Object obj) {
		try{
			hosp = new SQL_Hospital(this); //create connection
				
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;
				return hosp.updateHospital(hospital);				
			}
			if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				return doct.updateDoctor(doctor);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		try{
			hosp = new SQL_Hospital (this);
			
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;
				return hosp.deleteHospital(hospital);
			}
			/*if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				return doct.deleteDoctor(doctor);
			}*/
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
}
