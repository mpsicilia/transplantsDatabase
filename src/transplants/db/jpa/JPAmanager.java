package transplants.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transplants.db.dbInterface.DBManagerInterface;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class JPAmanager implements DBManagerInterface {

	private EntityManager em;
	private JPApatient pat= new JPApatient(this);
	private JPAdonor don=new JPAdonor(this);
	// RODRIGO: BEGIN
	private JPAorgan org = new JPAorgan(this);
	// RODRIGO: END

	public JPAmanager() {
		super();
		connect();
	}
	
	public EntityManager getEManager() {
		return em;
	}

	@Override
	public void connect() {
		try {
			em = Persistence.createEntityManagerFactory("transplant-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public boolean insert(Object obj) {
		try {
			if (Patient.class == obj.getClass()) {
				Patient patient = (Patient) obj;

				boolean r = pat.insert(patient);
				return r;
			}
			if (Donor.class == obj.getClass()) {
				Donor donor = (Donor) obj;
				return don.insert(donor);
			}
			// RODRIGO: BEGIN
			if (Organ.class == obj.getClass()) {
				Organ organ = (Organ) obj;

				boolean r = org.insert(organ);
				return r;
			}
			// RODRIGO: END

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
		// DONE IN JDBC
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
		return false;
	}
	
	public Hospital getHospitalPatient(Integer idhosp) {
		Hospital hosp = new Hospital();

		try {
			hosp = pat.getHospitalbyid(idhosp);
			return hosp;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hosp;
	}
	public Patient getPatient(Patient patient){
		Patient patito=new Patient();
		try{
			patito=pat.getPatient(patient);
			return patito;
			
			
		
	} catch (Exception ex) {
		ex.printStackTrace();
	}
		return patito;
	}

	@Override
	public boolean donorFKinOrgan(Integer idD, Integer idO) {
		// TODO Auto-generated method stub
		return false;
	}

	public Organ organOfADonor(Integer donorId) {		
		return don.selectOrgan(donorId);
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
		try {
			List<Donor> donorList = don.searchDonor(name);
			return donorList;
		} catch (Exception e) {
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
		try {
			List<Patient> patients = pat.searchPatient(name);
			return patients;
		} catch (Exception e) {
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
		// DONE WITH JDBC
		return null;
	}

	@Override
	public List<Doctor> selectAllDoctors() {
		// DONE WITH JDBC
		return null;
	}

	@Override
	public List<Patient> selectAllPatients() {
		try{
			List<Patient> list=pat.selectAllPatients();
			return list;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean update(Object obj) {
		em.getTransaction().begin();
		em.getTransaction().commit();
		return true;
	}

	@Override
	public boolean delete(Object obj) {
		if (Donor.class == obj.getClass()) {
			Donor donor = (Donor) obj;
			return don.removeDonor(donor);
		}
		if (Patient.class == obj.getClass()) {
			Patient patient = (Patient) obj;
			return pat.removePatient(patient);
		}
		if (Organ.class == obj.getClass()) {
			Organ organ = (Organ) obj;

			boolean r = org.delete(organ);
			return r;
		}
		return false;
	}

	@Override
	public Integer idPatient(Patient patient) {
		return 0;
		/*
		 
	Integer id = 30; // por ejemplo
		try {
			id = pat.getIdOfPatient(patient);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;*/

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
		return don.getIdOfDonor(d);
	}

	
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
		return don.getDonorOfOrgan(nameO);
	}

	@Override
	public List<Patient> dbCompatibilityTest(Organ organ) {
		// TODO Auto-generated method stub
		return null;
	}

}