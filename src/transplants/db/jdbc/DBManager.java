package transplants.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.Request;

import transplants.db.dbInterface.DBManagerInterface;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class DBManager implements DBManagerInterface{
	private Connection c;
	private SQL_Hospital hosp;
	private SQL_Doctor doct;
	private SQL_Patient pat;
	private SQL_AnimalTissue animalT;
	private SQL_Organ org;
	private SQL_Donor don;
	private SQL_Request req;

	
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
			Class.forName("org.sqlite.JDBC");//to create a connection
			this.c = DriverManager.getConnection("jdbc:sqlite:./db/transplant.db");//specific directory(.: actual current directory)
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	@Override
	public void disconnect() {
		try {
			c.close();//to close the connection
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean createTables() {
		try{
			
			hosp = new SQL_Hospital(this);
			doct = new SQL_Doctor(this);
			pat = new SQL_Patient(this);
			animalT = new SQL_AnimalTissue(this);
			org = new SQL_Organ(this);
			don = new SQL_Donor(this);
			req = new SQL_Request(this);
			
			hosp.createTable();
			doct.createTable();
			pat.createTable();
			animalT.createTable();
			org.createTable();
			don.createTable();
			req.createTable();
					
			return true;
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean dropTables(){
		try{
			
			hosp = new SQL_Hospital(this);
			doct = new SQL_Doctor(this);
			pat = new SQL_Patient(this);
			animalT = new SQL_AnimalTissue(this);
			org = new SQL_Organ(this);
			don = new SQL_Donor(this);
			req = new SQL_Request(this);
			
			hosp.dropTable();
			doct.dropTable();
			pat.dropTable();
			animalT.dropTable();
			org.dropTable();
			don.dropTable();
			req.dropTable();
					
			return true;
			
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		return false;
	}
	

	//insertions
	@Override
	public boolean insert(Object obj) {
			
		try{
				
			if (Hospital.class==obj.getClass()){
				//create connection
				hosp = new SQL_Hospital(this); 
				Hospital hospital=(Hospital)obj;			
				return hosp.insertHospital(hospital);
			}
			
			if (Doctor.class==obj.getClass()){
				doct = new SQL_Doctor (this);
				Doctor doctor=(Doctor)obj;			
				return doct.insertDoctor(doctor);
			}
			
			if(Patient.class==obj.getClass()){
				pat = new SQL_Patient (this);
				Patient patient=(Patient)obj;
				return pat.insertPatient(patient);
			}			
			if (Donor.class==obj.getClass()){
				don = new SQL_Donor (this); 
				Donor donor=(Donor)obj;
				return don.insertDonor(donor);
			}
			if (Organ.class==obj.getClass()){
				org = new SQL_Organ (this);
				Organ organ=(Organ)obj;
				return org.insertOrgan(organ);
			}
			if (Animal_tissue.class==obj.getClass()){
				animalT= new SQL_AnimalTissue(this);
				Animal_tissue animalTi=(Animal_tissue)obj;
				return animalT.insertAnimalTissue(animalTi);
			}
			if (Requested_organ.class==obj.getClass()){
				req= new SQL_Request(this);
				Requested_organ reqOrgan=(Requested_organ)obj;
				return req.insertRequest(reqOrgan);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//PKs
	@Override
	public boolean insertPrimaryKeyDoctorHospital(Integer id1, Integer id2){
		return hosp.insertHospitalsDoctors(id1, id2);
	}
	@Override
	public boolean insertPrimaryKeyDoctorPatient(Integer patId, Integer doctId){
		return doct.insertDoctorPatientTable(patId, doctId);
	}	
	@Override
	public boolean insertPrimaryKeyRequestedAnimal(Integer idRequest, Integer idAnimal){//falta llamarlo desde el main
		return animalT.insertRequestedAnimal(idRequest, idAnimal);
	}
	
	//FKs
	@Override
	public boolean insertFKinRequestedOrgan (int patID, int reqOrg){
		return req.insertPatientFK(patID, reqOrg);
	}
	@Override
	public boolean insertFKInPatient (Integer patID, Integer hospID){
		return pat.insertHospitalFK(patID, hospID);
	}
	@Override
	public boolean donorFKinOrgan (Integer idD, Integer idO){
		return org.insertDonorFK(idD, idO);
	}
	@Override
	public boolean requestedFKinOrgan (int idR, int idO){
		return org.insertRequestedFK (idR, idO);
	}
	
	//searches
	@Override
	public List<Hospital> searchHosp(String name) {	
		
		try{
			hosp = new SQL_Hospital(this); //create connection
			List<Hospital> hospList= hosp.searchHospital(name);
			return hospList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
}
	//new method to use in UIDOCTOR
	public String searchHospital(Integer id){
		try{
			hosp = new SQL_Hospital(this); //create connection
			String namehosp= hosp.Hospitalofdoctor(id);
			return namehosp;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
	@Override
	public List<Doctor> searchDoctor(String name) {
		try{
			doct = new SQL_Doctor(this); //create connection
			List<Doctor> doctList= doct.searchDoctor(name);
			return doctList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Animal_tissue> searchAnimalTissue(String name) {
		try{
			animalT = new SQL_AnimalTissue(this); //create connection
			List<Animal_tissue> animalTList= animalT.searchAnimalTissue(name);
			return animalTList;
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Donor> searchDonor(String name) {
		try{
			don = new SQL_Donor(this); //create connection
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
		try{
			org = new SQL_Organ(this);
			List<Organ> organList = org.searchOrgan(name);
			return organList;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Patient> searchPatient(String name) {
		try{
			pat = new SQL_Patient(this);
			List<Patient> patientList = pat.searchPatient(name);
			return patientList;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Requested_organ> searchRequest(String name) {
		try{
			req = new SQL_Request(this);
			List<Requested_organ> requestList = req.searchReqOrgan(name);
			return requestList;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	//selects
	@Override
	public List <Hospital> selectAllHospitals(){
		hosp= new SQL_Hospital(this);
		return hosp.selectAllHospitals();
	}
	@Override
	public List <Doctor> selectAllDoctors(){
		doct=new SQL_Doctor(this);
     	return doct.selectAllDoctors();
	}
	@Override
	public List<Patient> selectAllPatients(){
		pat=new SQL_Patient(this);
		return pat.selectAllPatients();
	}
	
	
	@Override
	public boolean update(Object obj) {
		try{							
			if (Hospital.class==obj.getClass()){
				//create connection
				hosp = new SQL_Hospital(this);
				Hospital hospital=(Hospital)obj;
				return hosp.updateHospital(hospital);				
			}
			if (Doctor.class==obj.getClass()){
				doct = new SQL_Doctor (this);
				Doctor doctor=(Doctor)obj;			
				return doct.updateDoctor(doctor);
			}
			if(Patient.class==obj.getClass()){
				pat = new SQL_Patient (this);
				Patient patient=(Patient)obj;
				return pat.updatePatient(patient);
			}	
			if (Donor.class==obj.getClass()){
				don =new SQL_Donor (this);
				Donor donor=(Donor)obj;
				return don.updateDonor(donor);
			}
			if (Organ.class==obj.getClass()){
				org= new SQL_Organ (this);
				Organ organ=(Organ)obj;
				return org.updateOrgan(organ);
			}
			if (Animal_tissue.class==obj.getClass()){
				animalT= new SQL_AnimalTissue(this);
				Animal_tissue animalTi=(Animal_tissue)obj;
				return animalT.updateAnimalTissue(animalTi);
			}
			if (Requested_organ.class==obj.getClass()){
				req= new SQL_Request (this);
				Requested_organ reqOrgan=(Requested_organ)obj;
				return req.updateReqOrgan(reqOrgan);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		try{			
			if (Hospital.class==obj.getClass()){
				hosp = new SQL_Hospital (this);
				Hospital hospital=(Hospital)obj;
				return hosp.deleteHospital(hospital);
			}
			if (Doctor.class==obj.getClass()){
				doct = new SQL_Doctor (this);
				Doctor doctor=(Doctor)obj;			
				return doct.deleteDoctor(doctor);
			}
			if(Patient.class==obj.getClass()){
				pat = new SQL_Patient(this);
				Patient patient=(Patient)obj;
				return pat.deletePatient(patient);
			}
			if (Donor.class==obj.getClass()){
				don  = new SQL_Donor(this);
				Donor donor=(Donor)obj;
				return don.deleteDonor(donor);
			}
			if (Organ.class==obj.getClass()){
				org= new SQL_Organ (this);
				Organ organ=(Organ)obj;
				return org.deleteOrgan(organ);
			}
			if (Animal_tissue.class==obj.getClass()){
				animalT=new SQL_AnimalTissue(this);
				Animal_tissue animalTi=(Animal_tissue)obj;
				return animalT.deleteAnimalTissue(animalTi);
			}
			if (Requested_organ.class==obj.getClass()){
				req= new SQL_Request(this);
				Requested_organ reqOrgan=(Requested_organ)obj;
				return req.deleteReqOrgan(reqOrgan);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return false;
	}


	//ID getters
	@Override
		public Integer idPatient (Patient patient){
			Integer id = 0;
			try{
				pat = new SQL_Patient(this);
				id = pat.getPatientID(patient);
			}catch (Exception e){
				e.printStackTrace();
			}
			return id;
		}
		@Override
		public Integer idRequestedOrgan (Requested_organ r){
			Integer id=0;
			try{
				req = new SQL_Request (this);
				id = req.getRequestedId(r);
			}catch (Exception ex){
				ex.printStackTrace();
			}
			return id;
		}
		@Override
		public Integer idOrgan (Organ o){
			Integer id=0;
			try{
				org = new SQL_Organ(this);
				id = org.getOrganId(o);
			}catch (Exception e){
				e.printStackTrace();
			}
			return id;
		}
		@Override
		public Integer idDonor (Donor d){
			Integer id=0;
			try{
				don = new SQL_Donor(this);
				id = don.getDonorID(d);
			}catch (Exception e){
				e.printStackTrace();
			}
			return id;
		}
		
	@Override
	public Integer getIdOfDoctor (Doctor doctor){
		return doct.getIdOfLastDoctor(doctor);
	}
		
	@Override
	//given a requested organ is going to return the patient
	public String patientReq (Requested_organ req){
		String namePat = "";
		try{
			pat = new SQL_Patient(this);
			namePat = pat.patientRequested(req.getId());
		}catch (Exception e){
			e.printStackTrace();
		}
		return namePat;
	}
	
	@Override
	//given a patient is going to return its requests
	public List<Requested_organ> characteristicsOfRequestedOrgans (int idPatient){
		List<Requested_organ> reqsOfPat = new ArrayList<Requested_organ>();
		try{
			req = new SQL_Request(this);
			reqsOfPat = req.characteristicsOfRequests(idPatient);
		}catch (Exception e){
			e.printStackTrace();
		}
		return reqsOfPat;
	}
	@Override
	//given a donor is going to return its organs
	public List<Organ> organsOfDonor (int idDonor){
		List<Organ> orgsOfDonor = new ArrayList<Organ>();
		try{
			org = new SQL_Organ(this);
			orgsOfDonor = org.organOfDonor(idDonor);
		}catch (Exception e){
			e.printStackTrace();
		}
		return  orgsOfDonor;
	}
	
	@Override
	public List<Doctor> doctorOfPatient(String pName) {
		List<Doctor> doctor = new ArrayList <Doctor>();
		try{
			doct = new SQL_Doctor(this);
			doctor = doct.doctorsAttendingPatient(pName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return doctor;
	}

	@Override
	public String hospitalOfPatient (String pName) {
		String hospital = "";
		try{
			hosp = new SQL_Hospital(this);
			hospital = hosp.hospitalOfPatient(pName);
		}catch (Exception e){
			e.printStackTrace();
		}
		return hospital;
	}

	@Override

	public List<Hospital> hospitalsOfDoctor(String name) {
		 List<Hospital> hospitals = new ArrayList<Hospital>();
		 try{
		     hosp = new SQL_Hospital(this);
			 hospitals = hosp.searchHospitalsOfDoctor(name);
			 
		  	}catch (Exception e){
		  			e.printStackTrace();
		  		}
			return hospitals;
	}
	
	@Override
	public Donor getDonorOfOrg (String nameO){
		Donor d = new Donor();
		try{
			don = new SQL_Donor(this);
			d = don.getDonorOfOrgan(nameO);
		}catch (Exception e){
			e.printStackTrace();
		}
		return d;
	}
	
	
}
