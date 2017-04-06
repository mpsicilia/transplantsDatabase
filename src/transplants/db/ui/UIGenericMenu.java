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
	
	private BufferedReader reader;
	private Integer option=0;
	private DBManager dbM;
	//a
	public static void main(String [] args){
		UIGenericMenu genericM= new UIGenericMenu();
	}
	
	private UIGenericMenu(){
		dbM=new DBManager();
		
		try{
			reader = new BufferedReader (new InputStreamReader (System.in));
	        while(true){
	        	System.out.println("MENU: ");
	        	System.out.println("1. Introduce a new hospital ");
	        	System.out.println("2. Introduce a new doctor ");
	        	 do{
	                    System.out.println("option[1-3]:");
	                    String leido= reader.readLine();
	                    option= Integer.parseInt(leido);            
	                }while (option<0|| option>3);
	        	 switch (option){
	        	 	case 1: {
	        	 		System.out.print("Name: ");
	        			String name = reader.readLine();
	        			//We are going to make phone number, address and postcode Strings to give the 
	        			//user more freedom for ex: (216)444-2200
	        			System.out.print("Phone number: ");
	        			String phone_number = reader.readLine();
	        			
	        			System.out.print("Address: ");
	        			String address = reader.readLine();
	        			
	        			System.out.print("City: ");
	        			String city = reader.readLine();
	        			
	        			System.out.print("Postcode: ");
	        			String post_code = reader.readLine();
	        			
	        			System.out.print("Country: ");
	        			String country = reader.readLine();
	        			
	        			Hospital hosp= new Hospital(name, phone_number, address, city, post_code, country);
	        			
	        	 		//Hospital hosp= db.Add_Hospital();
	        			boolean ok=dbM.insert(hosp);
	        			if (ok){
	        				System.out.print("Hospital introduced");
	        			}else{
	        				System.out.print("Hospital not introduced");
	        			}
	        			
	        	 		break;
	        	 	}
	        	 	case 2:{
	        	 		System.out.print("Registration number");
	        			String reg = reader.readLine();
	        			
	        			System.out.print("Specialization ");
	        			String spe = reader.readLine();
	        			Doctor doct= new Doctor(reg, spe);
	        			if (dbM.insert(doct)){
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
	}
	
}