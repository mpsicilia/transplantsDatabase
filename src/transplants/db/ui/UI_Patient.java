package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;

public class UI_Patient {
	
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_Patient(){		
	}
	
	public void introduceNewPatient (){
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
			boolean introduced = dbManager.insert(p);
			if(introduced){
				System.out.println("Patient has been introduced. ");
			}
			else{
				System.out.println("Patient has not been introduced. ");
			}
			
			
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public List<Patient> searchPatient(){
		try{
			System.out.println("Introduce the name of the patient: ");
	 		String name = console.readLine();	 	
			List<Patient> patients = dbManager.searchPatient(name);
	 		return patients;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; //se puede?
	}
	
	public void updatePatient(Patient p){
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
			boolean updated = dbManager.update(p);
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
	
	public void deletePatient (Patient pat){
		try{
			boolean deleted = dbManager.delete(pat);
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
	
	public void patientHospitalAndDoctor (String ptName){
		try{
			List<Doctor> patDoctors = dbManager.patientDoctor(ptName);
			String hospName = dbManager.patientHospital(ptName);
			System.out.println("Patient: " + ptName + ", is admitted in the hospital: " + hospName + 
					". The doctors that take care of him are: \n" + patDoctors);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
