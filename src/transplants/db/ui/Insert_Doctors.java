package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Insert_Doctors {
	public static void main(String args[]){
		try{
			System.out.println("Please, input the doctors information: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Registration number: ");
			String name = reader.readLine();

			System.out.print("Specialitation: ");
			String phone_number = reader.readLine();
			
					
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
