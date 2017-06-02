package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class UI_Patient {

	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public UI_Patient() {
	}
	//M: used by case 2/ case 4*/
	public Patient introduceNewPatient(JPAmanager jpaManager, DBManager dbmanager) {
		try {
			List<Hospital> hosps = dbmanager.selectAllHospitals();
			List<Doctor> docs = dbmanager.selectAllDoctors();
			if(hosps.isEmpty() && docs.isEmpty() ){
				System.out.println("There are no hospitals or doctors");
				System.out.println("You can not introduce a patient");
			}
			System.out.print("Name: ");
			String name = console.readLine();

			System.out.print("Birth date [yyyy-mm-dd]: ");
			String birth = console.readLine();
			Date birthDate = Date.valueOf(birth);

			System.out.print("Weight (kg): ");
			Float weight = Float.parseFloat(console.readLine());

			System.out.print("Height (cm): ");
			Float height = Float.parseFloat(console.readLine());

			System.out.print("Gender: ");
			String gender = console.readLine();

			System.out.print("Pathology: ");
			String path = console.readLine();

			System.out.print("Blood type: ");
			String bt = console.readLine();

			System.out.print("Life expectancy [yyyy-mm-dd]: ");
			Date life = Date.valueOf(console.readLine());
			LocalDate doa= LocalDate.now();
			Date addition= Date.valueOf(doa);
			System.out.println("Date of adition: " +addition);
			Patient p = new Patient(name, birthDate, weight, height, gender, path, bt, addition, life);

			boolean introduced = jpaManager.insert(p);			
			
			System.out.println("\nIntroduce the id of the hospital in which the patient is hospitalized. ");
			Iterator<Hospital> itH = hosps.iterator();
			while (itH.hasNext()) {
				Hospital h = itH.next();
				System.out.println(h);
			}
			
			Integer idHosp = Integer.parseInt(console.readLine());
			Hospital hospital = jpaManager.getHospitalPatient(idHosp);         
		
			hospital.addPatient(p);
			p.setHospital(hospital);
			boolean okUpdatepatient = jpaManager.update(p);
			boolean okUpdatehospital = jpaManager.update(hospital);

			// RELATIONSHIP BETWEEN DOCTOR AND PATIENT			
			System.out.println("How many doctors are attending the patient?");
			
			Iterator<Doctor> itD = docs.iterator();
			while (itD.hasNext()) {
				Doctor d = itD.next();
				System.out.println(d);
			}
			Integer Xtimes = Integer.parseInt(console.readLine());
			Integer counter = 1;
			Integer doctId = 0;
			boolean introduced2 = false;
			Integer patId = jpaManager.getIdPatient(p);
			Integer counterdoct=1;
			
			do {
				System.out.print("Introduce the id of the "+ counterdoct + "º doctor that is going to take care of the patient: ");
				doctId = Integer.parseInt(console.readLine());
				introduced2 = dbmanager.assigmentDoctorPatient(patId, doctId);
				counter++;
				counterdoct++;
			} while (counter <= Xtimes);

			if (introduced && introduced2 && okUpdatepatient && okUpdatehospital) {
				System.out.println("Patient has been introduced. ");
			} else {
				System.out.println("Patient has NOT been introduced. ");
			}

			return p;

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	//M: used from case2->case4*/
	public List<Patient> searchPatient(JPAmanager jpaManager) {
		try {
			System.out.println("Introduce the name of the patient: ");
			String name = console.readLine();
			List<Patient> patient = jpaManager.searchPatient(name);
			return patient;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//M: used by case 4/ case 1*/
	public void updatePatient(Patient p, JPAmanager jpaManager) {
		boolean again = true;
		try {
			while (again) {
				System.out.println("1. Name: ");
				System.out.println("2. Birth date. ");
				System.out.println("3. Weight. ");
				System.out.println("4. Height. ");
				System.out.println("5. Gender. ");
				System.out.println("6. Pathology. ");
				System.out.println("7. Blood type. ");
				System.out.println("8. Date of addition. ");
				System.out.println("9. Life expectancy. ");
				System.out.println("Choose the information that is going to be updated [1-9]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op) {
				case 1:
					System.out.println("Introduce the new name: ");
					p.setName(console.readLine());
					break;
				case 2:
					System.out.println("Introduce the new birth date: ");
					p.setBirthDate(Date.valueOf(console.readLine()));
					break;
				case 3:
					System.out.println("Introduce the new weight: ");
					p.setWeight(Float.parseFloat(console.readLine()));
					break;
				case 4:
					System.out.println("Introduce the new height: ");
					p.setHeight(Float.parseFloat(console.readLine()));
					break;
				case 5:
					System.out.println("Introduce the new gender: ");
					p.setGender(console.readLine());
					break;
				case 6:
					System.out.println("Introduce the new pathology: ");
					p.setPathology(console.readLine());
					break;
				case 7:
					System.out.println("Introduce the new blood type: ");
					p.setBloodType(console.readLine());
					break;
				case 8:
					System.out.println("Introduce the new date of addition: ");
					p.setAdditionDate(Date.valueOf(console.readLine()));
					break;
				case 9:
					System.out.println("Introduce the new life expectancy: ");
					p.setLifeExpectancy(Date.valueOf(console.readLine()));
					break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if ((console.readLine()).equals("no")) {
					again = false;
				}
			}
			boolean updated = jpaManager.update(p);
			if (updated) {
				System.out.println("Patient has been updated. \n" + p.toString());
			} else {
				System.out.println("Patient has NOT been updated. ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//M: used from case 4/ case 2*/
	public void deletePatient(Patient pat, JPAmanager jpaManager, DBManager dbmanager) {
		try {
			boolean reqorgansdeleted=false;
			List <Requested_organ> reqorgans = pat.getRequested_organ();
		
			for (Requested_organ reqorgan : reqorgans) {
				reqorgansdeleted= dbmanager.delete(reqorgan);
			}						
			boolean patientDeleted = jpaManager.delete(pat);		
			
			if(patientDeleted && (reqorgans.isEmpty() || reqorgansdeleted)){
				System.out.println("Patient has been deleted.");
			}
			else{
				System.out.println("Patient has not been deleted. ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//M: used in case 4/ case 3*/
	public void patientHospitalAndDoctor(String ptName, JPAmanager jpaManager, DBManager dbmanager) {
		try {
			List<Patient> patients=new ArrayList<>();
			List<Doctor> patDoctors=new ArrayList<>();
			String nameOfHosp = jpaManager.hospitalOfPatient(ptName);
			patients = jpaManager.searchPatient(ptName);
			Iterator<Patient> it1 = patients.iterator();
			while(it1.hasNext()){
				Patient p=it1.next();
				p.getName();
				patDoctors = dbmanager.doctorOfPatient(ptName);
			}
			

			System.out.println("Patient: " + ptName + ", is admitted in the hospital: " + nameOfHosp
					+ ". The doctors that take care of him are: \n");
			Iterator<Doctor> it = patDoctors.iterator();

			while (it.hasNext()) {
				Doctor d = it.next();
				System.out.println(d);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
