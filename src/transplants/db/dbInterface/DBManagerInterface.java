package transplants.db.dbInterface;

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
	
	//estos 4 de abajo son solo del dbmanager de jdbc
	public void connect();
	public void disconnect();
	
	public boolean createTables();
	public boolean dropTables();
	//insert para jpa y para jdbc
	public boolean insert(Object obj);
	
	public boolean insertPrimaryKeyDoctorHospital(Integer id1, Integer id2);	
	public boolean insertPrimaryKeyDoctorPatient(Integer id1, Integer id2);	
	public boolean insertPrimaryKeyRequestedAnimal(Integer id1, Integer id2);//falta hacerla
	
	public boolean insertFKinRequestedOrgan (int patID, int reqOrg);
	public boolean insertFKInPatient (Integer patID, Integer hospID);
	public boolean donorFKinOrgan (Integer idD, Integer idO);
	public boolean requestedFKinOrgan (int idR, int idO);
	
	public List<Hospital> searchHosp(String name); 
	public List<Animal_tissue> searchAnimalTissue(String name); 
	public List<Doctor> searchDoctor(String name); 
	public List<Donor> searchDonor(String name); 
	public List<Organ> searchOrgan(String name); 
	public List<Patient> searchPatient(String name); 
	public List<Requested_organ> searchRequest(String name); 
	
	public List <Hospital> selectAllHospitals();	
	public List <Doctor> selectAllDoctors();
	public List<Patient> selectAllPatients();
	
	public boolean update (Object obj);	
	public boolean delete (Object obj);
	
	public Integer idPatient (Patient patient);
	public Integer idRequestedOrgan (Requested_organ r);
	public Integer idOrgan (Organ o);
	public Integer idDonor (Donor d);
	
	public Integer getIdOfDoctor(Doctor doct);
	public String patientReq (Requested_organ req);
	public List<Requested_organ> characteristicsOfRequestedOrgans (int idPatient);
	public List<Organ> organsOfDonor (int idDonor);
	public String hospitalOfDoctor(String name);
	public String hospitalOfPatient(String pName);
	public List<Doctor> doctorOfPatient(String pName);
	public Donor getDonorOfOrg (String nameO);
	
}
