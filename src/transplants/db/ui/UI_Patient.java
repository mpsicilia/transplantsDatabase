package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Patient;

public class UI_Patient {
	
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_Patient(){		
	}
	
	public void introduceNewPatient (){
		try{
			System.out.println("Birth date [yyyy-mm-dd]: ");
			String birth = console.readLine();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate birthDate = LocalDate.parse(birth, formatter);
			
			System.out.println("Weight: ");
			Float weight = Float.parseFloat(console.readLine());
			
			System.out.println("Height: ");
			Float height =Float.parseFloat(console.readLine());
			
			System.out.println("Gender: ");
			String gender = console.readLine();
			
			System.out.println("Pathology: ");
			String path = console.readLine();
			
			System.out.println("Blood type: ");
			String bt = console.readLine();
			
			System.out.println("Date of addition: ");
			String doa = console.readLine();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate addition = LocalDate.parse(doa, format);
			
			System.out.println("Life expectancy: ");
			int life = Integer.parseInt(console.readLine());
			
			Patient p = new Patient (birthDate, weight, height, gender, path, bt, life, addition);
			boolean introduced = dbManager.insert(p);
			if(introduced){
				System.out.println("Patient has been introduced. ");
			}
			else{
				System.out.println("Patient has not been introduced. ");
			}
			
			
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}

}
