package transplants.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;


import transplants.db.dbInterface.DBManagerInterface;
import transplants.db.jdbc.SQL_Donor;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class JPAmanager implements DBManagerInterface {
	
	private EntityManager em;
	private JPAhospital hosp;
	
	private JPAdoctor doct;
	private JPApatient pat;
	private JPAdonor don;


	public JPAmanager(){
		super();
		connect();
	}
	
	public EntityManager getEManager(){
		return em;
	}
	
	@Override
	public void connect() {
		try{
			em = Persistence.createEntityManagerFactory("transplant-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	@Override
	public  boolean insert(Object obj) {
		try {

			if (Hospital.class == obj.getClass()) {
				// create connection
				hosp = new JPAhospital(this); 				
				Hospital hospital = (Hospital) obj;
				return hosp.insert(hospital);
			}
			
			if (Doctor.class == obj.getClass()) {
				doct = new JPAdoctor(this);
				Doctor doctor = (Doctor) obj;
				return doct.insert(doctor);
			} 
			if(Patient.class==obj.getClass()){ 
				pat = new JPApatient(this); 
				Patient patient=(Patient) obj; 
				return pat.insert(patient);
				} 
			if (Donor.class==obj.getClass()){
				don=new JPAdonor(this);
				Donor donor=(Donor) obj;
				return don.insert(donor);
			}
				 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void disconnect() {
		em.close();
		
	}
	@Override
	public boolean createTables() {
		// DONE IN JDBC
		return false;
	}
	@Override
	public boolean dropTables() {
		// DONE IN JDBC
		return false;
	}
	@Override
	public boolean insertPrimaryKeyDoctorHospital(Integer id1, Integer id2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean insertPrimaryKeyDoctorPatient(Integer id1, Integer id2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean insertPrimaryKeyRequestedAnimal(Integer id1, Integer id2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean insertFKinRequestedOrgan(int patID, int reqOrg) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean insertFKInPatient(Integer patID, Integer hospID) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean donorFKinOrgan(Integer idD, Integer idO) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean requestedFKinOrgan(int idR, int idO) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Hospital> searchHosp(String name) {
		// DONE WITH JDBC
		return null;
	}
	@Override
	public List<Animal_tissue> searchAnimalTissue(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Doctor> searchDoctor(String name) {
		// DONE WITH JDBC
		return null;
	}
	@Override
	public List<Donor> searchDonor(String name) {
		try{
			don = new JPAdonor(this); 
			List<Donor> donorList= don.searchDonor(name);
			return donorList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Organ> searchOrgan(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Patient> searchPatient(String name) {
		try{
			pat = new JPApatient(this); 
			List<Patient> patients= pat.searchPatient(name);
			return patients;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Requested_organ> searchRequest(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Hospital> selectAllHospitals() {
		//DONE WITH JDBC
		return null;
	}
	@Override
	public List<Doctor> selectAllDoctors() {
		// DONE WITH JDBC
		return null;
	}
	@Override
	public List<Patient> selectAllPatients() {
		this.getEManager().getTransaction().begin();
		Query q1 = em.createNativeQuery("SELECT * FROM Patients ", Patient.class);
		List<Patient> allpatients = (List<Patient>) q1.getResultList();
		return allpatients;
	}
	
	@Override
	public boolean update(Object obj) {
		// TODO Auto-generated method stub
    return false;
	}
	
	@Override
	public boolean delete(Object obj) {
		if (Donor.class==obj.getClass()){
			don=new JPAdonor(this);
			Donor donor=(Donor) obj;
			return don.removeDonor(donor);
		}
		if(Patient.class==obj.getClass()){
			pat=new JPApatient(this);
			Patient patient=(Patient)obj;
			return pat.removePatient(patient);
		}
		return false;
	}
	@Override
	public Integer idPatient(Patient patient) {
		Integer id=3000; //por ejemplo
		try {
			pat = new JPApatient(this);
			id = pat.getIdOfPatient(patient);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	
	}
	@Override
	public Integer idRequestedOrgan(Requested_organ r) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer idOrgan(Organ o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer idDonor(Donor d) {
		Integer id = 0;
		try {
			don = new JPAdonor(this);
			id = don.getIdOfDonor(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public Integer getIdOfDoctor(Doctor doct) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String patientReq(Requested_organ req) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Requested_organ> characteristicsOfRequestedOrgans(int idPatient) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Organ> organsOfDonor(int idDonor) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Hospital> hospitalsOfDoctor(String name) {
		// DONE WITH JDBC
		return null;
	}
	@Override
	public String hospitalOfPatient(String pName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Doctor> doctorOfPatient(String pName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Donor getDonorOfOrg(String nameO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String searchHospital(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> dbCompatibilityTest(Organ organ) {
		// TODO Auto-generated method stub
		return null;
	}

}