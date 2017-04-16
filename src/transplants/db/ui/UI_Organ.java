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
import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;

public class UI_Organ {
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_Organ(){
		
	}
	
	public void introduceNewOrgan(){
		try{
			System.out.print("Name: ");
			String name = console.readLine();
			
			System.out.print("Weight: ");
			Float weight = Float.parseFloat(console.readLine());
				
			System.out.print("Type of donation [total or partial]: ");
			String typeOfDonation = console.readLine();
			
			Organ organ= new Organ(name, weight, typeOfDonation); 
			
			boolean ok=dbManager.insert(organ);
			if (ok){
				System.out.print("Organ has been introduced");
			}else{
				System.out.print("Organ has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public List<Organ> searchOrgan(){
		try{
			System.out.println("Introduce the name of the organ: ");
	 		String name = console.readLine();	 	
			List<Organ> organ = dbManager.searchOrgan(name);
	 		return organ;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	public void updateOrgan(Organ organ){
		boolean again = true;	
		try{
			while(again){
				System.out.println("Choose the information that is going to be updated [1-7]: ");
				System.out.println("1. Name");
				System.out.println("2. Weight");
				System.out.println("3. Type of donation");				
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						organ.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new weight: ");
						organ.setWeight(Float.parseFloat(console.readLine()));
						break;
					case 3:
						System.out.println("Introduce the new type of donation: ");
						organ.setTypeOfDonation(console.readLine());
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = dbManager.update(organ);
			if(updated){
				System.out.println("Organ has been updated. \n"
						+ organ.toString());
			}
			else{
				System.out.println("Organ has NOT been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	public void deleteOrgan (Organ organ){
		try{
			boolean deleted = dbManager.delete(organ);
			if(deleted){
				System.out.println("Organ has been deleted.");
			}
			else{
				System.out.println("Organ has NOT been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	
	}
}