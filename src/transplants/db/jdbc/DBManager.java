package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import transplants.db.dbInterface.DBManagerInterface;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class DBManager implements DBManagerInterface {
	private Connection c;
	private SQL_Hospital hosp=new SQL_Hospital(this);
	private SQL_Doctor doct=new SQL_Doctor(this);
	private SQL_Patient pat= new SQL_Patient(this);
	private SQL_AnimalTissue animalT= new SQL_AnimalTissue(this);
	private SQL_Organ org= new SQL_Organ(this);
	private SQL_Donor don= new SQL_Donor(this);
	private SQL_Request req = new SQL_Request(this);
	
	public DBManager() {
		super();
		connect();
	}

	public Connection getC() {
		return c;
	}

	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");// to create a connection
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void disconnect() {
		try {
			c.close();// to close the connection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override/*m:used*/
	public void expired(){
		org.deleteExpiredOrgans();
	}

	@Override
	public boolean createTables() {
		try {
			hosp.createTable();
			doct.createTable();
			pat.createTable();
			animalT.createTable();
			org.createTable();
			org.viewAvailablePatients();
			don.createTable();
			req.createTable();
			req.viewAvailableDonors();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean dropTables() {
		try {
			hosp.dropTable();
			doct.dropTable();
			pat.dropTable();
			animalT.dropTable();
			org.dropTable();
			org.dropViewAvailablePatients();
			don.dropTable();
			req.dropTable();
			req.dropViewAvailableDonors();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	// insertions
	@Override
	public boolean insert(Object obj) {

		try {
			if (Doctor.class == obj.getClass()) {
				Doctor doctor = (Doctor) obj;
				return doct.insertDoctor(doctor);
			}
			if (Animal_tissue.class == obj.getClass()) {
				Animal_tissue animalTi = (Animal_tissue) obj;
				return animalT.insertAnimalTissue(animalTi);
			}
			if (Requested_organ.class == obj.getClass()) {
				Requested_organ reqOrgan = (Requested_organ) obj;
				return req.insertRequest(reqOrgan);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

	// PKs
	/*M: used from uidoctor:introducenewdoctor*/
	@Override
	public boolean assigmentDoctorHospital(Integer id1, Integer id2) {
		return hosp.insertHospitalsDoctors(id1, id2);
	}
	//C: used from ui_gen*/
	@Override
	public boolean assigmentDoctorPatient(Integer patId, Integer doctId) {
		return doct.insertDoctorPatientTable(patId, doctId);
	}

	@Override
	//C: USED from ui_animal*/
	public boolean assigmentRequestedAnimal(Integer idRequest, Integer idAnimal) {
		return animalT.insertRequestedAnimal(idRequest, idAnimal);
	}
	
	// FKs
	//C: USED from ui_req*/
	@Override
	public boolean assigmentPatientRequest(int patID, int reqOrg) {
		return req.insertPatientFK(patID, reqOrg);
	}

	@Override
	//USED*/
	public boolean requestedFKinOrgan(int idR, int idO) {
		return org.insertRequestedFK(idR, idO);
	}
	//used*/
	@Override
	public int reqIdByPatIdAndDonOrg (int patId, String org){
		return req.reqIdByPatIdAndDonOrg(patId, org);
	}
	
	public int orgIdByDonIdAndReqOrg (int donId, String reqOrg){
		return org.orgIdByDonIdAndReqOrg(donId, reqOrg);
	}
	
	public void uiDeleteExpiredOrgans(){
		org.deleteExpiredOrgans();
	}

	
	// searches
	@Override
	//M: in use by uihosp: searchHospital*/
	public List<Hospital> searchHosp(String name) {
		try {
			List<Hospital> hospList = hosp.searchHospital(name);
			return hospList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//M: used in uidoctor: searchDoctor*/
	@Override
	public List<Doctor> searchDoctor(String name) {
		try {
			List<Doctor> doctList = doct.searchDoctor(name);
			return doctList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//M: used in updateanimaltissue*/
	public Animal_tissue animalTissueByIdReq (int idr){
		try{
			return animalT.getAnimalOfRequestedOrgan(idr);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Donor> searchDonor(String name) {
		// DONE WITH JPA
		return null;
	}
	
	@Override
	public List<Patient> searchPatient(String name) {
		//DONE IN JPA
		return null;
	}
	

	@Override
	public List<Patient> searchAllPatients(Hospital hospital){
		//done in jpa
		return null;
	}

	/*M: used by uipatient: introducenewpatient/ uidoctor:introducenewdoctor*/
	@Override
	public List<Hospital> selectAllHospitals() {
		return hosp.selectAllHospitals();
	}

	//M: used by uipatient: introduceNewPatient*/
	@Override
	public List<Doctor> selectAllDoctors() {
		return doct.selectAllDoctors();
	}

	//M: used*/
	@Override
	public List<Patient> dbCompatibilityTest(Organ organ) {
		return org.compatibilityTest(organ);
	}
	
	public List<Donor> dbCompatiblePatientOrgans(Requested_organ reqOrgan){
		return req.compatiblePatientOrgans(reqOrgan);
	}

	//M: using hospital and doctor, organ, reqorgan, animal*/
	@Override
	public boolean update(Object obj) {
		try {
			if (Hospital.class == obj.getClass()) {
				Hospital hospi = (Hospital) obj;
				return hosp.updateHospital(hospi);
			}			
			if (Doctor.class == obj.getClass()) {
				Doctor doctor = (Doctor) obj;
				return doct.updateDoctor(doctor);
			}
			if (Organ.class == obj.getClass()) {
				Organ organ = (Organ) obj;
				return org.updateOrgan(organ);
			}
			if (Animal_tissue.class == obj.getClass()) {
				Animal_tissue animalTi = (Animal_tissue) obj;
				return animalT.updateAnimalTissue(animalTi);
			}
			if (Requested_organ.class == obj.getClass()) {
				Requested_organ reqOrgan = (Requested_organ) obj;
				return req.updateReqOrgan(reqOrgan);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	//M: hospital uses it + doctor +reqOrgan*/
	@Override
	public boolean delete(Object obj) {
		try {
			if (Hospital.class == obj.getClass()) {
				Hospital hospital = (Hospital) obj;
				return hosp.deleteHospital(hospital);
			}
			if (Doctor.class == obj.getClass()) {
				Doctor doctor = (Doctor) obj;
				return doct.deleteDoctor(doctor);
			}
			if (Requested_organ.class == obj.getClass()) {
				Requested_organ reqOrgan = (Requested_organ) obj;
				return req.deleteReqOrgan(reqOrgan);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer getIdPatient(Patient patient) {//delete from here once we have it in jpa
		//return pat.getPatientID(patient);
		//done in JPA
		return 0;
	}
	/*M: used from uidoctor: insertnewdoctor*/
	@Override
	public Integer getIdOfDoctor(Doctor doctor) {
		return doct.getIdOfLastDoctor(doctor);
	}
	
	public int getIdFromLastReqOrg(Requested_organ reqOrgan){
		return req.getIdFromLastReqOrg(reqOrgan);
	}
	
	//C:USED from ui_requested and ui_animal*/
	@Override
	public Integer idRequestedOrgan(Requested_organ r) {
		Integer id = 0;
		try {
			id = req.getRequestedId(r);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}

	//C: USED from ui_animal*/
	public Integer idOfAnimal(Animal_tissue animalTissue){
		Integer id = 0;
		try {
			id = animalT.getAnimalId(animalTissue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	//c: USED
	@Override
	public Hospital getHospitalPatient(Integer id){
		//done in JPA
		return null;
	}
	
	@Override
	//M: used by uirequOrgan: characteristicsOfReqOrg*/
	// given a patient is going to return its requests
	public List<Requested_organ> characteristicsOfRequestedOrgans(int idPatient) {
		List<Requested_organ> reqsOfPat = new ArrayList<Requested_organ>();
		try {
			reqsOfPat = req.characteristicsOfRequests(idPatient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reqsOfPat;
	}
	
	@Override
	//M: in use by uihosps: doctroHospital*/
	public List<Hospital> hospitalsOfDoctor(String name) {
		List<Hospital> hospitals = new ArrayList<Hospital>();
		try {
			hosp = new SQL_Hospital(this);
			hospitals = hosp.searchHospitalsOfDoctor(name);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitals;
	}
	@Override
	public String hospitalOfPatient(String pName) {
		//done in jpa
		return null;
	}
	//M: used by uipatient: patientHospitalAndDoctor*/
	@Override
	public List<Doctor> doctorOfPatient(String pName) {
		List<Doctor> doctor = new ArrayList<Doctor>();
		try {
			doctor = doct.doctorsAttendingPatient(pName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctor;
	}

	//M: used
	@Override
	public List<Doctor> workingDoctorsInHosp (String hospName){
		List<Doctor> docs = new ArrayList<Doctor> ();
		try{
			doct = new SQL_Doctor(this);
			docs = doct.doctorsWorkingInHospital(hospName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return docs;
	}
	
	//M: used by unmarshal in uihospital
	@Override
	public boolean updateUnmarshalledHosp (Hospital h){
		try{
			return hosp.updateHospital(h);
		}catch (Exception e){			
		}
		return false;
	}
	
	//M: used by cacse2/case5*/
	public Organ organOfRequested (Requested_organ req){
		try{
			Organ o = org.organThatSuppliesRequest(req);
			return o;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
