package transplants.db.ui;

import java.io.*;
import java.util.List;

import transplants.db.jdbc.SQL_Hospital;
import transplants.db.pojos.Hospital;

public class Insert_Hospitals {
	
	
	public static void main(String args[]){		
		SQL_Hospital hospital= new SQL_Hospital();
		try{
			BufferedReader console= new BufferedReader (new InputStreamReader (System.in));
	        int opcion=0;
	        while(true){
	        	System.out.println("MENU: ");
	        	System.out.println("1. Introduce a new hospital ");
	        	System.out.println("2. Show the information about a specific hospital");
	        	System.out.println("3. Log out ");
	        	 do{
	                    System.out.println("Introduzca una opcion [1-3]:");
	                    String leido= console.readLine();
	                    opcion= Integer.parseInt(leido);            
	                }while (opcion<0|| opcion>3);
	        	 switch (opcion){
	        	 	case 1: 
	        	 		Hospital hosp= Add_Hospital();
	        	 		hospital.Insert_Hospital(hosp);
	        	 	case 2: {
	                      String name_hospital;
	                        do{
	                            System.out.println("Introduce the name of the hospital that you want: ");
	                            name_hospital= console.readLine();
	                            try{
	                               List< Hospital> hospToShow= hospital.Search_Hospital(name_hospital);
	                               //Hay que arreglar esto... pensarlo mañana en clase
	                               printList(hospToShow);
	                                break;
	                            }catch (Exception ex){
	                                ex.printStackTrace();
	                            }
	                        }while (name_hospital!=null);
	                        break;
	        	 	}
	        	 	case 3:
	        	 		System.exit(0);
	        	 }
	        }
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	public static void printList(List <Hospital> hospToShow){
		//printearlo como en el de bacterias desde el toString
	}

	public static Hospital Add_Hospital(){
		Hospital hosp=null;
		try{
			System.out.println("Please, introduce the hospital information: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
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
			hosp= new Hospital(name, phone_number, address, city, post_code, country);
						
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return hosp;
	}
}
