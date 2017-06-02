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
	
	
	public void connect();
	public void disconnect();
	
	public boolean createTables();
	public boolean dropTables();
	public void expired();
	
	public boolean insert(Object obj);
	public boolean assigmentDoctorHospital(Integer id1, Integer id2);	
	public boolean assigmentDoctorPatient(Integer id1, Integer id2);	
	public boolean assigmentRequestedAnimal(Integer id1, Integer id2);
	public boolean assigmentPatientRequest (int patID, int reqOrg);
	public boolean requestedFKinOrgan (int idR, int idO);
	public int reqIdByPatIdAndDonOrg (int patId, String org);
	public int orgIdByDonIdAndReqOrg (int donId, String reqOrg);
	
	public List<Hospital> searchHosp(String name); 
	public List<Doctor> searchDoctor(String name); 
	public List<Donor> searchDonor(String name); 
	public List<Patient> searchPatient(String name); 
	public List<Patient> searchAllPatients(Hospital hospital);
	
	public List <Hospital> selectAllHospitals();	
	public List <Doctor> selectAllDoctors();
	public List<Patient> dbCompatibilityTest(Organ organ); //used
	public List<Donor> dbCompatiblePatientOrgans(Requested_organ reqOrgan); //used
	
	public boolean update (Object obj);	
	public boolean delete (Object obj);
	
	public Integer getIdPatient (Patient patient);
	public Integer getIdOfDoctor(Doctor doct);
	public Integer idRequestedOrgan (Requested_organ r);
	public Integer idOfAnimal(Animal_tissue animalTissue);
	
	public Hospital getHospitalPatient(Integer idhosp);
	public List<Requested_organ> characteristicsOfRequestedOrgans (int idPatient);
	public List<Hospital> hospitalsOfDoctor(String name);
	public String hospitalOfPatient(String pName);
	
	public List<Doctor> doctorOfPatient(String pName);
	public List<Doctor> workingDoctorsInHosp (String hospName);
	public boolean updateUnmarshalledHosp (Hospital h);
	public Organ organOfRequested (Requested_organ req); 
	
}
