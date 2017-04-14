package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Doctor;


public class UI_Doctor {
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_Doctor(){
	}
	
	public void introduceNewDoctor(){
		try{
			System.out.print("Name of the doctor: ");
			String name = console.readLine();
			
			System.out.print("Registration number: ");
			String regNumber = console.readLine();
			
			System.out.print("Specialization: ");
			String specializ = console.readLine();
			
			Doctor doct= new Doctor(name, regNumber, specializ);
			boolean ok=dbManager.insert(doct);
			if (ok){
				System.out.print("The doctor has been introduced correctly");
			}else{
				System.out.print("The doctor has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public List<Doctor> searchDoctor(){
		try{
			System.out.println("Introduce the name of the doctor: ");
	 		String name = console.readLine();	 	
			List<Doctor> doctor = dbManager.searchDoctor(name);
	 		return doctor;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public void updateDoctor(Doctor doct){
		boolean again = true;	
		try{
			while(again){
				System.out.println("1. Name of the doctor");
				System.out.println("2. Registration number");
				System.out.println("3. Specialization");
				System.out.println("Choose the information that is going to be updated [1-3]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						doct.setNameOfDoctor(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new registration number: ");
						doct.setRegistrationNumber(console.readLine());
						break;
					case 3:
						System.out.println("Introduce the new specialization: ");
						doct.setSpecialization(console.readLine());
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = dbManager.update(doct);
			if(updated){
				System.out.println("Doctor information has been updated. \n"
						+ doct.toString());
			}
			else{
				System.out.println("Doctor information has NOT been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	public void deleteDoctor (Doctor doc){
		try{
			boolean deleted = dbManager.delete(doc);
			if(deleted){
				System.out.println("Doctor has been deleted.");
			}
			else{
				System.out.println("Doctor has not been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	
	}
}
