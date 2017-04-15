package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.omg.CORBA.Request;

import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public interface DBManagerInterface {
	
	public void connect();
	public void disconnect();
	
	public boolean createTables();
	public boolean dropTables();
	
	public boolean insert(Object obj);
	
	public List<Hospital> searchHosp(String name); 
	public List<Animal_tissue> searchAnimalTissue(String name); 
	public List<Doctor> searchDoctor(String name); 
	public List<Donor> searchDonor(String name); 
	public List<Organ> searchOrgan(String name); 
	public List<Patient> searchPatient(String name); 
	public List<Requested_organ> searchRequest(String name); 
	
	public boolean update (Object obj);	
	public boolean delete (Object obj);
	
	public List <Hospital> DoctorHospital (String name);
	public List<Doctor> patientDoctor (String pName);
	public String patientHospital (String pName);
	
}
