package transplants.db.jpa;

import java.util.ArrayList;
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
import transplants.db.pojos.TransplantDatabase;

public class JPAmanager implements DBManagerInterface {

	private EntityManager em;
	private JPApatient pat = new JPApatient(this);
	private JPAdonor don = new JPAdonor(this);
	// RODRIGO: BEGIN
	private JPAorgan org = new JPAorgan(this);
	// RODRIGO: END
	private JPAhospital hosp = new JPAhospital(this);

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
			if (Hospital.class == obj.getClass()) {
				Hospital hospital = (Hospital) obj;

				boolean r = hosp.insert(hospital);
				return r;
			}

			if (Patient.class == obj.getClass()) {
				Patient patient = (Patient) obj;

				boolean r = pat.insert(patient);
				return r;
			}
			if (Donor.class == obj.getClass()) {
				Donor donor = (Donor) obj;
				
				boolean r = don.insert(donor);
				return r;
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

	
	//NEW
	public Integer getIdpatient(Patient patient){
		Integer patid;
		try{
			patid=pat.getIdpatient(patient);
			
			return patid;
		}
		 catch (Exception ex) {
				ex.printStackTrace();
			}
		return null;
		
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
		Hospital hospital = new Hospital();

		try {
			hospital = hosp.getHospitalbyid(idhosp);
			return hospital;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hospital;
	}

	/*public Patient getPatient(Patient patient) {
		Patient patito = new Patient();
		try {
			patito = pat.getPatient(patient);
			return patito;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return patito;
	}*/
	//NEW
	public Hospital getHospital (Hospital hospital){
		Hospital hospi = new Hospital();
		try {
			hospi = hosp.getHospital(hospital);
			return hospi;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hospi;
	}
	

	public boolean donorFKinOrgan(Integer idD, Integer idO) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Organ> organOfADonor(Integer donorId) {
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
	//NEW
	public List<Patient> searchallpatients(Hospital hospital){
		try {
			List<Patient> patients = hosp.searchAllPatients(hospital);
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
	//DONE WITH JDBC
	
	public List<Hospital> selectAllHospitals() {
		/*try {
			List<Hospital> list = hosp.selectAllHospitals();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;

		
	}

	@Override
	public List<Doctor> selectAllDoctors() {
		// DONE WITH JDBC
		return null;
	}

	@Override
	public List<Patient> selectAllPatients() {
		try {
			List<Patient> list = pat.selectAllPatients();
			return list;
		} catch (Exception e) {
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
		if(Hospital.class== obj.getClass()){
			Hospital hospital=(Hospital) obj;
			boolean r=  hosp.removeHospital(hospital);
			return r;
		}
		return false;
	}
	@Override
	public String hospitalOfPatient(String pName) {
		Hospital hospital=new Hospital();
		try{
			
			hospital=hosp.hospitalofpatient(pName);
			
			return hospital.getName();
		
	}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hospital.getName();
		}
		
  //FOR WHAT DO I USE IT?
	@Override
	public Integer idPatient(Patient patient) {
		
		 
		 Integer id = 30; 
		 try { 
			 id = pat.getIdpatient(patient);
		  return id; 
		  } 
		 catch (Exception e) {
		  e.printStackTrace(); 
		  } 
		 return id;
		 

	}
	public List<Patient> searchPatbyname(String name){
		//Patient patient=new Patient();
		List<Patient> patients=new ArrayList<>();
		 try { 
			 patients=pat.searchPatientbyname(name);
			 return patients;
			
		  } 
		 catch (Exception e) {
		  e.printStackTrace(); 
		  } 
		
		return patients;
	}
	//used in unmarshall
	public Patient getPatientById (Integer idP){
		Patient pat = new Patient();
		try{
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return pat;
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
	public Integer idDonor(Donor d) {//por ahora este metodo no lo usamos
		// TODO Auto-generated method stub
		// don.getIdOfDonor(d);
		return null;
	}

	
	public Integer getIdOfDoctor(Doctor doct) {
		//DONE WITH JDBC
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