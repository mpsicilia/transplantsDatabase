package transplants.db.ui;

import java.io.*;

public class Insert_Hospitals {
	public static void main(String args[]){
		try{
			System.out.println("Please, input the hospital information: ");
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
		
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
