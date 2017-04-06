package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.jdbc.SQL_Hospital;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class UIGenericMenu {

	//private BufferedReader reader;
	//private Integer option;
	
	public static void main (String []args){
		DBManager dmanager = new DBManager();
		
		try{
			BufferedReader console= new BufferedReader (new InputStreamReader (System.in));
	        int opcion=0;
	        while(true){
	        	System.out.println("MENU: ");
	        	System.out.println("1. Introduce a new hospital ");
	        	System.out.println("2. Introduce a new doctor ");
	        	 do{
	                    System.out.println("option[1-3]:");
	                    String leido= console.readLine();
	                    opcion= Integer.parseInt(leido);            
	                }while (opcion<0|| opcion>3);
	        	 switch (opcion){
	        	 	case 1: {
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
	        			
	        	 		//Hospital hosp= dmanager.Add_Hospital();
	        			boolean ok=dmanager.insert(hosp);
	        			if (ok){
	        				System.out.print("Hospital introduced");
	        			}else{
	        				System.out.print("Hospital not introduced");
	        			}
	        			
	        	 		break;
	        	 	}
	        	 	case 2:{
	        	 		System.out.print("Registration number");
	        			String reg = console.readLine();
	        			
	        			System.out.print("Specialization ");
	        			String spe = console.readLine();
	        			Doctor doct= new Doctor(reg, spe);
	        			if (dmanager.insert(doct)){
	        				System.out.print("Doctor introduced");
	        			}else{
	        				System.out.print("Doctor not introduced");
	        			}
	        	 		break;
	        	 	}
	        	 	}
	        	 		 		System.exit(0);
	        	 }
	        
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	
}}