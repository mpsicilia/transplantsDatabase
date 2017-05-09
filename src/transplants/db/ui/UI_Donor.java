package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;

import java.util.List;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Donor;

import transplants.db.pojos.Organ;

public class UI_Donor {

	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	//atributo añadido
		private JPAmanager jpamanager=new JPAmanager();
	
	public UI_Donor(){
		
	}
	
	public Donor introduceNewDonor(JPAmanager jpaManager){
		Donor donor = new Donor();
		try{
			System.out.print("Name: ");
			String name = console.readLine();

			System.out.println("Date of birth: [yyyy-mm-dd]: ");
			String birth = console.readLine();
			Date birthDate = Date.valueOf(birth);
			
			System.out.print("Height: ");
			Float height = Float.parseFloat(console.readLine());
			
			System.out.print("Weight: ");
			Float weight = Float.parseFloat(console.readLine());
			
			System.out.print("Gender: ");
			String gender= console.readLine();
			
			System.out.print("Dead or Alive: ");
			String deadAlive = console.readLine();
			
			System.out.print("Blood Type: ");
			String bloodType = console.readLine();
			
			donor= new Donor(name, birthDate, weight, height, gender, deadAlive, bloodType); 
			//there is no relationship between donor and doctor, nor donor and hospital
			/*int idDonor = dbManager.getIdOfDonor (donor);
			
			System.out.print("Introduce the id of the hospital in which the donor is located: ");
			dbManager.selectAllHospitals();
			Integer idHospYouChoose = Integer.parseInt(console.readLine());
			boolean okHosp = dbManager.insertFKHospInDonor (idHospYouChoose, idDonor);
			
			
			System.out.print("Introduce the id of the doctor that takes care of the donor: ");
			dbManager.selectAllDoctors();
			Integer idDoctYouChoose = Integer.parseInt(console.readLine());*/
			
			
			
			boolean ok=jpaManager.insert(donor);
			if (ok){
				System.out.print("Donor has been introduced");
			}else{
				System.out.print("Donor has NOT been introduced");
			}
			//linea de abajo añadida para añadir con JPA
			jpamanager.insert(donor);
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return donor;
	}
	
	public List<Donor> searchDonor(JPAmanager jpaManager){
		try{
			System.out.println("Introduce the name of the donor: ");
	 		String name = console.readLine();	 	
			List<Donor> donor = jpaManager.searchDonor(name);
	 		return donor;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public void updateDonor(Donor donor, JPAmanager jpaManager){
		boolean again = true;	
		try{
			while(again){
				System.out.println("Choose the information that is going to be updated [1-7]: ");
				System.out.println("1. Name");
				System.out.println("2. Date of birth");
				System.out.println("3. Height");
				System.out.println("4. Weight");
				System.out.println("5. Gender");
				System.out.println("6. Dead or Alive");
				System.out.println("7. Blood Type");				
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						donor.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new date of birth: ");
						donor.setBirthDate(Date.valueOf(console.readLine()));
						break;
					case 3:
						System.out.println("Introduce the new height: ");
						donor.setHeight(Float.parseFloat(console.readLine()));
						break;
					case 4:
						System.out.println("Introduce the new weight: ");
						donor.setWeight(Float.parseFloat(console.readLine()));
						break;
					case 5:
						System.out.println("Introduce the new gender: ");
						donor.setGender(console.readLine());
						break;
					case 6:
						System.out.println("Introduce the new state of life: ");
						donor.setDeadOrAlive(console.readLine());
						break;
					case 7:
						System.out.println("Introduce the new type of blood: ");
						donor.setBloodType(console.readLine());
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = jpaManager.update(donor);
			if(updated){
				System.out.println("Donor has been updated. \n"
						+ donor.toString());
			}
			else{
				System.out.println("Donor has NOT been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	public void deleteDonor (Donor donor, JPAmanager jpaManager){
		try{
			boolean deleted = jpaManager.delete(donor);
			if(deleted){
				System.out.println("Donor has been deleted.");
			}
			else{
				System.out.println("Donor has NOT been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	

	
}
