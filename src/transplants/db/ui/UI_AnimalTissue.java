package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Requested_organ;

public class UI_AnimalTissue {
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_AnimalTissue(){		
	}
	
	//Insertion of an animal tissue
	public void introduceNewAnimalTissue(List<Requested_organ> reOrg, DBManager dbManager, String typetissue){
		try{
			System.out.print("Name of the animal where the tissue come from: ");
			String name = console.readLine();
			
			System.out.print("Pathology of the patient: ");
			String pathology = console.readLine();
				
			System.out.print("Time the tissue lasts before the transplant [yyyy-mm-dd]: ");
			Date lifeExpTissue= Date.valueOf(console.readLine());
			
			Animal_tissue animalT= new Animal_tissue(name, typetissue, pathology, lifeExpTissue);
			boolean ok=dbManager.insert(animalT);
			
			Integer idAnimal= dbManager.idOfAnimal(animalT);
			Integer idRequest= 0;
			boolean idGotOk= false;
			
			for(Requested_organ reqOrgan: reOrg){
				idRequest= dbManager.idRequestedOrgan(reqOrgan);
				idGotOk= dbManager.assigmentRequestedAnimal(idRequest, idAnimal);
			}
			
			if (ok && idGotOk){
				System.out.println("The animal tissue has been introduced");
			}else{
				System.out.println("The animal tissue has NOT been introduced");
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	//Getting the animal tissue that supplies the requested organ
	public Animal_tissue animalTissueOfRequested (Integer idReq, DBManager dbManager){
		Animal_tissue at = new Animal_tissue();
		try{
			at = dbManager.animalTissueByIdReq(idReq);
		}catch (Exception e){
			e.printStackTrace();
		}
		return at;
	}
	
	//Update of the information of an animal tissue
	public void updateAnimalTissue(Animal_tissue animalT, DBManager dbManager){
		boolean again = true;	
		try{
			while(again){
				System.out.println("Choose the information that is going to be updated [1-4]: ");
				System.out.println("1. Name");
				System.out.println("2. Type of tissue");
				System.out.println("3. Pathology ");
				System.out.println("4. Time the tissue lasts before being transplanted");				
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
						animalT.setLifeExpTissue(Date.valueOf(console.readLine()));
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
	
}
