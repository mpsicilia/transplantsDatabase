package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Hospital;

public class UI_AnimalTissue {
	private DBManager dbManager=new DBManager();
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_AnimalTissue(){		
	}
	
	
	public void introduceNewAnimalTissue(){
		try{
			System.out.print("Name of the animal tissue: ");
			String name = console.readLine();

			System.out.print("Type of tissue of the animal: ");
			String typeOfTissue = console.readLine();
			
			System.out.print("Pathology: ");
			String pathology = console.readLine();
				
			System.out.print("Country: ");
			Integer timeLasts = Integer.parseInt(console.readLine());
			
			Animal_tissue animalT= new Animal_tissue(name, typeOfTissue, pathology, timeLasts);
			boolean ok=dbManager.insert(animalT);
			if (ok){
				System.out.print("The animal tissue has been introduced");
			}else{
				System.out.print("The animal tissue has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
		
	public List<Animal_tissue> searchAnimalTissue(){
		try{
			System.out.println("Introduce the name of the animal Tissue: ");
	 		String name = console.readLine();	 	
			List<Animal_tissue> animalT = dbManager.searchAnimalTissue(name);
	 		return animalT;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; 
	}
	
	
	public void updateAnimalTissue(Animal_tissue animalT){
		boolean again = true;	
		try{
			while(again){
				System.out.println("Choose the information that is going to be updated [1-4]: ");
				System.out.println("1. Name");
				System.out.println("2. Type of tissue");
				System.out.println("3. Pathology ");
				System.out.println("4. Time the tissue lasts");				
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						animalT.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new type of tissue: ");
						animalT.setTypeOfTissue(console.readLine());
						break;
					case 3:
						System.out.println("Introduce the new pathology: ");
						animalT.setPathology(console.readLine());
						break;
					case 4:
						System.out.println("Introduce the new time the tissue lasts: ");
						animalT.setTimeItLasts(Integer.parseInt(console.readLine()));
						break;

				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			boolean updated = dbManager.update(animalT);
			if(updated){
				System.out.println("Animal Tissue has been updated. \n"
						+ animalT.toString());
			}
			else{
				System.out.println("Animal Tissue has NOT been updated. ");
			}
			
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	
	
	public void deleteAnimalTissue (Animal_tissue animalT){
		try{
			boolean deleted = dbManager.delete(animalT);
			if(deleted){
				System.out.println("Animal tissue has been deleted.");
			}
			else{
				System.out.println("Animal tissue has NOT been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void insertRequestedAnimalFK (){
		try{
			System.out.println("");
		}catch (IOException ex){
			ex.printStackTrace();
		}
	}
}
