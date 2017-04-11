package transplants.db.ui;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Hospital;

public class UI_Hospitals {
	
	private DBManager dbManager=new DBManager();
	BufferedReader console;
	
	public UI_Hospitals(){		
	}
	
	public void introduceNewHospital(){
		try{
			console= new BufferedReader (new InputStreamReader (System.in));
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
	
	public void searchHospital(){
		try{
			System.out.println("Introduce the name of the hospital: ");
	 		String name = console.readLine();	 		
	 		List <Hospital> hospitals = dbManager.searchHosp(name);
	 		//para acordarme de como recorrer la lista he mirado como lo hice en la practica de java
	 		Iterator <Hospital> it = hospitals.iterator();
	 		while (it.hasNext()){
	 			Hospital h = it.next();
	 			System.out.println(h);
	 		}
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
		
}
