package transplants.db.ui;

import java.io.*;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Hospital;

public class UI_Hospitals {
	
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_Hospitals(){		
	}
	
	public void introduceNewHospital(){
		try{
			System.out.print("Name: ");
			String name = console.readLine();
			//We are going to make phone number, address and postcode Strings to give the 
			//user more freedom for ex: (216)444-2200
			System.out.print("Phone number: ");
			String phone_number = console.readLine();
			
			System.out.print("Address: ");
			String address = console.readLine();
			
			System.out.print("City: ");
			String city = console.readLine();
			
			System.out.print("Postcode: ");
			String post_code = console.readLine();
			
			System.out.print("Country: ");
			String country = console.readLine();
			
			Hospital hosp= new Hospital(name, phone_number, address, city, post_code, country);
			boolean ok=dbManager.insert(hosp);
			if (ok){
				System.out.print("Hospital has been introduced");
			}else{
				System.out.print("Hospital has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
		
	public List<Hospital> searchHospital(){
		try{
			System.out.println("Introduce the name of the hospital: ");
	 		String name = console.readLine();	 	
			List<Hospital> hospitals = dbManager.searchHosp(name);
	 		return hospitals;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; //se puede?
	}
	
	
	public void updateHospital(Hospital hosp){
		boolean again = true;	
		try{
			while(again){
				System.out.println("1. Name");
				System.out.println("2. Phone number");
				System.out.println("3. Address");
				System.out.println("4. City");
				System.out.println("5. Postcode");
				System.out.println("6. Country");
				System.out.println("Choose the information that is going to be updated [1-6]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						hosp.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new phone number: ");
						hosp.setPhone_number(console.readLine());
						break;
					case 3:
						System.out.println("Introduce the new address: ");
						hosp.setAddress(console.readLine());
						break;
					case 4:
						System.out.println("Introduce the new city: ");
						hosp.setCity(console.readLine());
						break;
					case 5:
						System.out.println("Introduce the new postcode: ");
						hosp.setPostcode(console.readLine());
						break;
					case 6:
						System.out.println("Introduce the new country: ");
						hosp.setCountry(console.readLine());
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = dbManager.update(hosp);
			if(updated){
				System.out.println("Hospital has been updated. \n"
						+ hosp.toString());
			}
			else{
				System.out.println("Hospital has not been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	
	public void deleteHospital (Hospital hosp){
		try{
			boolean deleted = dbManager.delete(hosp);
			if(deleted){
				System.out.println("Hospital has been deleted.");
			}
			else{
				System.out.println("Hospital has not been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
		
}
