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
	EntityManager em;
	private JPAhospital hosp;
	private JPAdoctor doct;
	private JPApatient pat;
	private JPAdonor don;
	
	

	
	public JPAmanager(){
		try{
	em = Persistence.createEntityManagerFactory("transplants-provider").createEntityManager();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	//NUEVO METODO. CREO QUE EL EM se crea una unica vez, en los JPA individuales
	public EntityManager getEManager(){
		return em;
	}

	@Override
	public  boolean insert(Object obj) {
		// JPAS NECESITAN CONSTRUCTOR AL QUE SE LE PASE UN ENTITY MANAGER?
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
	public void connect() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean createTables() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean dropTables() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Animal_tissue> searchAnimalTissue(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Doctor> searchDoctor(String name) {
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
	public List<Requested_organ> searchRequest(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Hospital> selectAllHospitals() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Doctor> selectAllDoctors() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Patient> selectAllPatients() {
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
	@Override
	public Integer idPatient(Patient patient) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
	public String hospitalOfDoctor(String name) {
		// TODO Auto-generated method stub
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

}