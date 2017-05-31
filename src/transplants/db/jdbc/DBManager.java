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

	@Override
	public boolean createTables() {
		try {
			hosp.createTable();
			doct.createTable();
			pat.createTable();
			animalT.createTable();
			org.createTable();
			don.createTable();
			req.createTable();

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
			don.dropTable();
			req.dropTable();

			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	// insertions
	//M: used by doctor
	@Override
	public boolean insert(Object obj) {

		try {

			if (Hospital.class == obj.getClass()) {
				Hospital hospital = (Hospital) obj;
				return hosp.insertHospital(hospital);
			}

			if (Doctor.class == obj.getClass()) {
				Doctor doctor = (Doctor) obj;
				return doct.insertDoctor(doctor);
			}

			if (Organ.class == obj.getClass()) {
				Organ organ = (Organ) obj;
				return org.insertOrgan(organ);
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
	//M: used
	@Override
	public boolean insertPrimaryKeyDoctorHospital(Integer id1, Integer id2) {
		return hosp.insertHospitalsDoctors(id1, id2);
	}

	@Override
	public boolean insertPrimaryKeyDoctorPatient(Integer patId, Integer doctId) {
		return doct.insertDoctorPatientTable(patId, doctId);
	}

	@Override
	public boolean insertPrimaryKeyRequestedAnimal(Integer idRequest, Integer idAnimal) {
		return animalT.insertRequestedAnimal(idRequest, idAnimal);
	}

	// FKs
	@Override
	public boolean insertFKinRequestedOrgan(int patID, int reqOrg) {
		return req.insertPatientFK(patID, reqOrg);
	}

	@Override
	public boolean insertFKInPatient(Integer patID, Integer hospID) {
		return pat.insertHospitalFK(patID, hospID);
	}

	@Override
	public boolean donorFKinOrgan(Integer idD, Integer idO) {
		return org.insertDonorFK(idD, idO);
	}

	@Override
	public boolean requestedFKinOrgan(int idR, int idO) {
		return org.insertRequestedFK(idR, idO);
	}

	// searches
	@Override
	//M: in use
	public List<Hospital> searchHosp(String name) {

		try {
			List<Hospital> hospList = hosp.searchHospital(name);
			return hospList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	public String searchHospital(Integer id) {
		try {
			hosp = new SQL_Hospital(this); 
			String namehosp = hosp.Hospitalofdoctor(id);
			return namehosp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	//M: used
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

	@Override
	public List<Animal_tissue> searchAnimalTissue(String name) {
		try {
			List<Animal_tissue> animalTList = animalT.searchAnimalTissue(name);
			return animalTList;
		} catch (Exception e) {
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
	public List<Organ> searchOrgan(String name) {
		try {
			List<Organ> organList = org.searchOrgan(name);
			return organList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Requested_organ> searchRequest(String name) {
		try {
			List<Requested_organ> requestList = req.searchReqOrgan(name);
			return requestList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//M: used
	@Override
	public List<Hospital> selectAllHospitals() {
		return hosp.selectAllHospitals();
	}

	@Override
	public List<Doctor> selectAllDoctors() {
		return doct.selectAllDoctors();
	}

	@Override
	public List<Patient> selectAllPatients() {
		return pat.selectAllPatients();
	}

	@Override
	public List<Patient> dbCompatibilityTest(Organ organ) {
		return org.CompatibilityTest(organ);
	}

	//M: using hospital and doctor
	@Override
	public boolean update(Object obj) {
		try {
			if (Hospital.class == obj.getClass()) {
				Hospital hospital = (Hospital) obj;
				return hosp.updateHospital(hospital);
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

	//M: hospital uses it + doctor
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
			if (Organ.class == obj.getClass()) {
				Organ organ = (Organ) obj;
				return org.deleteOrgan(organ);
			}
			if (Animal_tissue.class == obj.getClass()) {
				Animal_tissue animalTi = (Animal_tissue) obj;
				return animalT.deleteAnimalTissue(animalTi);
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

	// METHODS IN ORDER TO GET THE ID
	@Override
	public Integer idPatient(Patient patient) {//delete from here once we have it in jpa
		return pat.getPatientID(patient);
	}

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

	@Override
	public Integer idOrgan(Organ o) {
		Integer id = 0;
		try {
			id = org.getOrganId(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	public Integer idOfAnimal(Animal_tissue animalTissue){
		Integer id = 0;
		try {
			id = animalT.getAnimalId(animalTissue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Integer idDonor(Donor d) {
		//DONE IN JPA
		return null;
	}

	//M: used
	@Override
	public Integer getIdOfDoctor(Doctor doctor) {
		return doct.getIdOfLastDoctor(doctor);
	}

	@Override
	// given a requested organ is going to return the patient
	public String patientReq(Requested_organ req) {
		String namePat = "";
		try {
			namePat = pat.patientRequested(req.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namePat;
	}

	@Override
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
	// given a donor is going to return its organs
	public List<Organ> organsOfDonor(int idDonor) {
		List<Organ> orgsOfDonor = new ArrayList<Organ>();
		try {
			orgsOfDonor = org.organOfDonor(idDonor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgsOfDonor;
	}

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

	@Override
	public String hospitalOfPatient(String pName) {
		String hospital = "";
		try {
			hospital = hosp.hospitalOfPatient(pName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospital;
	}
	

	@Override
	//M: in use
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
	public Donor getDonorOfOrg(String nameO) {
		//DONE IN JPA
		return null;
	}

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
}
