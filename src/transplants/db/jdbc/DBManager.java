package transplants.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
	private SQL_Patient pat;
	private SQL_AnimalTissue animalT;
	private SQL_Organ org;
	private SQL_Donor don;
	private SQL_Request req;

	
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
			//create connections
			hosp = new SQL_Hospital(this); 
			doct = new SQL_Doctor (this);
			pat = new SQL_Patient (this);			
				
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;			
				return hosp.insertHospital(hospital);
			}
			
			if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				return doct.insertDoctor(doctor);
			}
			
			if(Patient.class==obj.getClass()){
				Patient patient=(Patient)obj;
				return pat.insertPatient(patient);
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
			//create connections
			hosp = new SQL_Hospital(this); 
			doct = new SQL_Doctor (this);
				
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
			doct = new SQL_Doctor (this);
			
			if (Hospital.class==obj.getClass()){
				Hospital hospital=(Hospital)obj;
				return hosp.deleteHospital(hospital);
			}
			if (Doctor.class==obj.getClass()){
				Doctor doctor=(Doctor)obj;			
				return doct.deleteDoctor(doctor);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createTables() {
		try{
			
			hosp = new SQL_Hospital(this);
			doct = new SQL_Doctor(this);
			pat = new SQL_Patient(this);
			animalT = new SQL_AnimalTissue(this);
			org = new SQL_Organ(this);
			don = new SQL_Donor(this);
			req = new SQL_Request(this);
			
			hosp.createTable();
			doct.createTable();
			pat.createTable();
			animalT.createTable();
			org.createTable();
			don.createTable();
			req.createTable();
					
			return true;
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean dropTables(){
		try{
			
			hosp = new SQL_Hospital(this);
			doct = new SQL_Doctor(this);
			pat = new SQL_Patient(this);
			animalT = new SQL_AnimalTissue(this);
			org = new SQL_Organ(this);
			don = new SQL_Donor(this);
			req = new SQL_Request(this);
			
			hosp.dropTable();
			doct.dropTable();
			pat.dropTable();
			animalT.dropTable();
			org.dropTable();
			don.dropTable();
			req.dropTable();
					
			return true;
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List <Hospital> DoctorHospital(String name) {
		List <Hospital> hospital = new ArrayList <Hospital>();
		try{
			hosp = new SQL_Hospital(this);
			hospital = hosp.searchDoctorInHospital(name);
		}catch (Exception e){
			e.printStackTrace();
		}
		return hospital;
	}
}
