package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class UI_Patient {
	
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	//atributo añadido
		private JPAmanager jpamanager=new JPAmanager();
	public UI_Patient(){		
	}
	
	public Patient introduceNewPatient (JPAmanager jpaManager){
		try{
			System.out.println("Name: ");
			String name = console.readLine();
			
			System.out.println("Birth date [yyyy-mm-dd]: ");
			String birth = console.readLine();
			Date birthDate = Date.valueOf(birth);
			
			System.out.println("Weight: ");
			Float weight = Float.parseFloat(console.readLine());
			
			System.out.println("Height: ");
			Float height =Float.parseFloat(console.readLine());
			
			System.out.println("Gender: ");
			String gender = console.readLine();
			
			System.out.println("Pathology: ");
			String path = console.readLine();
			
			System.out.println("Blood type: ");
			String bt = console.readLine();
			
			System.out.println("Date of addition: ");
			String doa = console.readLine();
			Date addition = Date.valueOf(doa);
			
			System.out.println("Life expectancy: ");
			int life = Integer.parseInt(console.readLine());
			
			Patient p = new Patient (name, birthDate, weight, height, gender, path, bt, life, addition);
			//here we introduce the patient
			boolean introduced = jpaManager.insert(p);
			//getting the FK
			Integer patId=jpaManager.idPatient(p);
			
			System.out.println("Introduce the id of the hospital in which the patient is hospitalized. ");
			List <Hospital>hosps= jpaManager.selectAllHospitals();
			Iterator <Hospital> itH=hosps.iterator();
			while (itH.hasNext()){
				Hospital h=itH.next();
				System.out.println(h);
			}
			Integer idHosp= Integer.parseInt(console.readLine());			
			//here we are introducing the FK hospital_id in table patients
			boolean introduced2=jpaManager.insertFKInPatient(patId,idHosp);
			
			
			System.out.println("How many doctors are attending the patient?");
			Integer Xtimes= Integer.parseInt(console.readLine());
			Integer counter=1;
			Integer doctId=0;
			boolean introduced3=false;
			do {
				System.out.println("Introduce the id of the doctor that is going to take care of the patient. ");
				List <Doctor> docs = jpaManager.selectAllDoctors();
				Iterator <Doctor> itD = docs.iterator();
				while (itD.hasNext()){
					Doctor d = itD.next();
					System.out.println(d);
				}		
				doctId = Integer.parseInt(console.readLine());
				introduced3= jpaManager.insertPrimaryKeyDoctorPatient(patId, doctId);
				counter++;
			}while(counter<=Xtimes);
			
			if(introduced && introduced2 && introduced3){
				System.out.println("Patient has been introduced. ");
			}
			else{
				System.out.println("Patient has not been introduced. ");
			}
			//linea de abajo añadida para añadir con JPA
			jpamanager.insert(p);
			return p;
			
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<Patient> searchPatient(JPAmanager jpaManager){
		try{
			System.out.println("Introduce the name of the patient: ");
	 		String name = console.readLine();	 	
			List<Patient> patients = jpaManager.searchPatient(name);
	 		return patients;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public void updatePatient(Patient p, JPAmanager jpaManager){
		boolean again = true;
		try{
			while(again){
				System.out.println("1. Name: ");
				System.out.println("2. Birth date. ");
				System.out.println("3. Weight. ");
				System.out.println("4. Height. ");
				System.out.println("5. Gender. ");
				System.out.println("6. Pathology. ");
				System.out.println("7. Blood type. ");
				System.out.println("8. Date of addition. ");
				System.out.println("9. Life expectancy. ");
				System.out.println("Choose the information that is goign to be updated [1-9]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op){
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
						p.setLifeExpectancy(Integer.parseInt(console.readLine()));
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			boolean updated = jpaManager.update(p);
			if(updated){
				System.out.println("Patient has been updated. \n"
						+ p.toString());
			}
			else{
				System.out.println("Patient has not been updated. ");
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}
	
	public void deletePatient (Patient pat, JPAmanager jpaManager){
		try{
			boolean deleted = jpaManager.delete(pat);
			if(deleted){
				System.out.println("Patient has been deleted.");
			}
			else{
				System.out.println("Patient has not been deleted. ");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void patientHospitalAndDoctor (String ptName, JPAmanager jpaManager){
		try{
			String nameOfHosp= jpaManager.hospitalOfPatient(ptName);
			List<Doctor> patDoctors = jpaManager.doctorOfPatient(ptName);
			
			System.out.println("Patient: " + ptName + ", is admitted in the hospital: " + nameOfHosp + 
					". The doctors that take care of him are: \n" + patDoctors);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Patient> allPatients (JPAmanager jpaManager){
		List<Patient> patList = new ArrayList<Patient>();
		try{
			patList = jpaManager.selectAllPatients();
		}catch (Exception e){
			e.printStackTrace();
		}
		return patList;
	}
}
