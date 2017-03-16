package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Insert_Organs {
	public static void main(String args[]){
		try{
			System.out.println("Please, input the information of the organ that is "
					+ "going to be donated: ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("Name of the organ: ");
			String name = reader.readLine();

			System.out.print("Weight: ");
			String string_weight= reader.readLine();			
			float weight = Float.parseFloat(string_weight);
			
			System.out.print("Type of donation: ");//HACERLO CON ENUMERACIONES
			String typeDonation = reader.readLine();
			
			}catch(IOException ex){
			ex.printStackTrace();
		}
	}
}
