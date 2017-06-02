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

public class JPAmanager implements DBManagerInterface {

	private EntityManager em;
	private JPApatient pat = new JPApatient(this);
	private JPAdonor don = new JPAdonor(this);
	private JPAorgan org = new JPAorgan(this);
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

	// M: used by hosp, donor, organ
	//C: and pat
	@Override
	public boolean insert(Object obj) {
		try {
			if (Hospital.class == obj.getClass()) {
				Hospital hospital = (Hospital) obj;
				return hosp.insert(hospital);
			}

			if (Patient.class == obj.getClass()) {
				Patient patient = (Patient) obj;
				return pat.insert(patient);
			}
			if (Donor.class == obj.getClass()) {
				Donor donor = (Donor) obj;
				return don.insert(donor);
			}

			if (Organ.class == obj.getClass()) {
				Organ organ = (Organ) obj;
				return org.insert(organ);
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
	public boolean assigmentDoctorHospital(Integer id1, Integer id2) {
		// DONE IN JDBC
		return false;
	}

	@Override
	public boolean assigmentDoctorPatient(Integer id1, Integer id2) {
		// DONE IN JDBC
		return false;
	}

	@Override
	public boolean assigmentRequestedAnimal(Integer id1, Integer id2) {
		// DONE IN JDBC
		return false;
	}

	@Override
	public boolean assigmentPatientRequest(int patID, int reqOrg) {
		// DONE IN JDBC
		return false;
	}
	
	@Override
	public boolean requestedFKinOrgan(int idR, int idO) {
		//done in jdbc
		return false;
	}
	@Override
	public int reqIdByPatIdAndDonOrg (int patId, String Org){
		//done in jdbc
		return 0;
	}
	
	@Override
	public List<Hospital> searchHosp(String name) {
		// DONE WITH JDBC
		return null;
	}	

	@Override
	public List<Doctor> searchDoctor(String name) {
		// DONE WITH JDBC
		return null;
	}
	
	@Override
	public List<Animal_tissue> searchAnimalTissue(String name) {
		// DONE IN JDBC
		return null;
	}
	
	// M: used by uidonor: searchDonor
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
	
	//M: used from uipatient: searchPatient (jpamanager)
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
	public List<Organ> searchOrgan(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Requested_organ> searchRequest(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// M: used in uihosps: seeallpatients
	@Override
	public List<Patient> searchAllPatients(Hospital hospital) {
		try {
			List<Patient> patients = hosp.searchAllPatients(hospital);
			return patients;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Hospital> selectAllHospitals() {
		//done in jdbc
		return null;
	}

	@Override
	public List<Doctor> selectAllDoctors() {
		// DONE WITH JDBC
		return null;
	}
	
	@Override
	public List<Patient> dbCompatibilityTest(Organ organ) {
		// Done in jdbc
		return null;
	}
	
	// used by organ, donor, patietn, hospital
	@Override
	public boolean update(Object obj) {
		em.getTransaction().begin();
		em.getTransaction().commit();
		return true;
	}
	
	// used by donor, organ, patient
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
				return org.delete(organ);				
			}
			return false;
		}		
		
	// C: used from ui_patient
	@Override
	public Integer getIdPatient(Patient patient) {
			Integer patid;
			try {
				patid = pat.getIdpatient(patient);
				return patid;
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return null;
	}
	@Override
	public Integer getIdOfDoctor(Doctor doct) {
		// DONE WITH JDBC
		return null;
	}
	@Override
	public Integer idRequestedOrgan(Requested_organ r) {
		// Done in jdbc
		return null;
	}
	
	@Override
	public Integer idOrgan(Organ o) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer idOfAnimal(Animal_tissue animalTissue){
		//done in jdbc
		return null;
	}

	@Override
	public Integer idDonor(Donor d) {// por ahora este metodo no lo usamos
		// TODO Auto-generated method stub
		// don.getIdOfDonor(d);
		return null;
	}
	
	//M: used from uipatient: introduceNewPatient
	@Override
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
	@Override
	public String patientReq(Requested_organ req) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Requested_organ> characteristicsOfRequestedOrgans(int idPatient) {
		// Done in jdbc
		return null;
	}
	
	@Override
	public List<Organ> organsOfDonor(int idDonor) {
		// Done in JDBC
		return null;
	}
	@Override
	public List<Hospital> hospitalsOfDoctor(String name){
		//done in jdbc
		return null;
	}
	//M: used from uipatient: patientHospitalAndDoctor
	@Override
	public String hospitalOfPatient(String pName) {
		Hospital hospital = new Hospital();
			try {
				hospital = hosp.hospitalofpatient(pName);
				return hospital.getName();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return hospital.getName();
	}
		
	@Override
	public List<Doctor> doctorOfPatient(String pName) {
			// Done in JDBC
			return null;
	}
		
	@Override
	public Donor getDonorOfOrg(Integer idOrgan) {
			return don.getDonorOfOrgan(idOrgan);
	}
	@Override
	public List<Doctor> workingDoctorsInHosp (String hospName){
		//done in jdbc
		return null;
	}
	@Override
	public boolean updateUnmarshalledHosp (Hospital h){
		//done in jdbc
		return false;
	}

	
	// NEW
	public Hospital getHospital(Hospital hospital) {
		Hospital hospi = new Hospital();
		try {
			hospi = hosp.getHospital(hospital);
			return hospi;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return hospi;
	}

	// used in unmarshall
	public Patient getPatientById(Integer idP) {
		Patient p = new Patient();
		try {
			p=pat.searchPatientById(idP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public Organ organOfRequested(Requested_organ req) {
		//M:DONE IN JDBC
		return null;
	}

}