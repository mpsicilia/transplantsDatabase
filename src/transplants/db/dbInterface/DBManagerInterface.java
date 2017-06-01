package transplants.db.dbInterface;

import java.util.List;
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
	public boolean assigmentDoctorHospital(Integer id1, Integer id2);	
	public boolean assigmentDoctorPatient(Integer id1, Integer id2);	
	public boolean assigmentRequestedAnimal(Integer id1, Integer id2);
	public boolean assigmentPatientRequest (int patID, int reqOrg);
	public boolean requestedFKinOrgan (int idR, int idO);//vamos a usarlo?
	public int reqIdByPatIdAndDonOrg (int patId, String Org);
	
	public List<Hospital> searchHosp(String name); 
	public List<Doctor> searchDoctor(String name); 
	public List<Animal_tissue> searchAnimalTissue(String name); //are we going to use it?
	public List<Donor> searchDonor(String name); 
	public List<Patient> searchPatient(String name); 
	public Patient searchPatient (Integer idPat);
	public List<Organ> searchOrgan(String name); //not used(jdbc)...gonna need it?
	public List<Requested_organ> searchRequest(String name); //not used(jdbc)...gonna need it?
	public List<Patient> searchAllPatients(Hospital hospital);
	
	public List <Hospital> selectAllHospitals();	
	public List <Doctor> selectAllDoctors();
	public List<Patient> selectAllPatients();//este habria que borralo
	public List<Patient> dbCompatibilityTest(Organ organ);
	
	public boolean update (Object obj);	
	public boolean delete (Object obj);
	
	public Integer getIdPatient (Patient patient);
	public Integer getIdOfDoctor(Doctor doct);
	public Integer idRequestedOrgan (Requested_organ r);
	public Integer idOrgan (Organ o);//este todavia no lo usamos....
	public Integer idOfAnimal(Animal_tissue animalTissue);
	public Integer idDonor (Donor d);//este todavia no lo usamos (jpa)
	
	public Hospital getHospitalPatient(Integer idhosp);
	public String patientReq (Requested_organ req);//not used....

	
	public List<Requested_organ> characteristicsOfRequestedOrgans (int idPatient);
	public List<Organ> organsOfDonor (int idDonor);
	public List<Hospital> hospitalsOfDoctor(String name);
	public String hospitalOfPatient(String pName);
	public List<Doctor> doctorOfPatient(String pName);
	public Donor getDonorOfOrg (String nameO);
	
}
