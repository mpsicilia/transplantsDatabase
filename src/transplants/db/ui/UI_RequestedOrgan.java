package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Animal_tissue;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;
import transplants.db.pojos.Requested_organ;

public class UI_RequestedOrgan {
	BufferedReader console = new BufferedReader (new InputStreamReader (System.in));
	
	public UI_RequestedOrgan(){
		
	}
	//M: used by uigeneric: case4/case4/case1*/
	public List<Requested_organ> introduceNewReqOrgan(Patient p, DBManager dbManager, JPAmanager jpam){
		List<Requested_organ> reqOrg= new ArrayList<>();
		try{
			boolean more = true;//one patient can request many organs so...
			while (more){
				System.out.print("Name: ");
				String name = console.readLine();
				
				System.out.print("Maximum Weight(kg): ");
				Float maxWeight = Float.parseFloat(console.readLine());
				
				System.out.print("Minimun Weight(kg): ");
				Float minWeight = Float.parseFloat(console.readLine());
				
				Requested_organ reqOrgan= new Requested_organ(name, maxWeight, minWeight); 
				boolean ok=dbManager.insert(reqOrgan);
				
				//get the id of the patient
				int idPatient =jpam.getIdPatient(p);
				
				//get the id of the  requested organ
				int idRequested =  dbManager.idRequestedOrgan(reqOrgan);				
				boolean okFK = dbManager.assigmentPatientRequest(idPatient, idRequested);
				boolean organAdded=reqOrg.add(reqOrgan);
				
				if (ok && okFK && organAdded){
					System.out.println("The request Organ has been introduced.\n");
					dbManager.expired();					
					if(reqOrgan.getName().equalsIgnoreCase("collagen")|| reqOrgan.getName().equalsIgnoreCase("skin")){
					uiCompatiblePatientOrgans(reqOrgan, dbManager);
					}
				}else{
					System.out.println("The request Organ has NOT been introduced. \n");
				}
				System.out.println("Is the patient going to request another organ? [yes/no]");
				String another = console.readLine();
				if(another.equalsIgnoreCase("no")){
					more = false;
				}
			
			}
			return reqOrg;
			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public List<Requested_organ> searchReqOrgan(DBManager dbManager){
		try{
			System.out.println("Introduce the name of the requested organ: ");
	 		String name = console.readLine();	 	
			List<Requested_organ> reqOrgan = dbManager.searchRequest(name);
	 		return reqOrgan;
		}catch (IOException ex){
			ex.printStackTrace();
		}
		return null; //
	}
	//M: used from case4/ case4/case 2*/
	public void updateReqOrgan(Requested_organ reqOrgan, DBManager dbManager){
		boolean again = true;
		try{
				while(again){
			
				System.out.println("Choose the information that is going to be updated [1-3]: ");
				System.out.println("1. Name");
				System.out.println("2. Maximum Weight");
				System.out.println("3. Minimum Weight");				
				int op = Integer.parseInt(console.readLine());
				switch (op){
					case 1:
						System.out.println("Introduce the new name: ");
						reqOrgan.setName(console.readLine());
						break;
					case 2:
						System.out.println("Introduce the new maximum weight: ");
						reqOrgan.setMaxWeight(Float.parseFloat(console.readLine()));
						break;
					case 3:
						System.out.println("Introduce the new minimum weight: ");
						reqOrgan.setMinWeight(Float.parseFloat(console.readLine()));
						break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if((console.readLine()).equals("no")){
					again =  false;
				}
			}
			
			
			boolean updated = dbManager.update(reqOrgan);
			if(updated){
				System.out.println("Request Organ has been updated and it comes from . \n"
						+ reqOrgan.toString());//see the toString
				dbManager.expired();
				uiCompatiblePatientOrgans(reqOrgan, dbManager);
			}
			else{
				System.out.println(" Request Organ has NOT been updated. ");
			}
			//}
			}catch (IOException ex){
				ex.printStackTrace();
			}
	}
	//M: case4/case4/case3*/
	public void deleteRequestOrgan (Requested_organ reqOrgan, DBManager dbManager){
		try{
			boolean deleted = dbManager.delete(reqOrgan);
			if(deleted){
				System.out.println("Requested Organ has been deleted.");
			}
			else{
				System.out.println("Requested Organ has NOT been deleted. ");
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	
	}
	public String patientOfRequested (Requested_organ reqOrg, DBManager dbManager) {
		String namePat = "";
		try{
			namePat = dbManager.patientReq(reqOrg);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return namePat;
	}
	//M: used by case4/case 4*/
	public List<Requested_organ> characteristicsOfRequestedOrgans (int idPat, DBManager dbManager){
		List<Requested_organ> requests = new ArrayList<Requested_organ>();
		try{
			requests = dbManager.characteristicsOfRequestedOrgans(idPat);
		}catch (Exception e){
			e.printStackTrace();
		}
		return requests;
	}
	
	//used
	public void uiCompatiblePatientOrgans(Requested_organ reqOrgan, DBManager dbManager) {
		List<Donor> compatibleDonors = new ArrayList<Donor>();
		List<Donor> donors= new ArrayList<Donor>();
		try {
			compatibleDonors = dbManager.dbCompatiblePatientOrgans(reqOrgan);
			Iterator<Donor> donorIterator = compatibleDonors.iterator();
			
			int counterDon = 1;
			Boolean hasNext= donorIterator.hasNext();
			if(!hasNext){
				System.out.println("At this moment there are no compatible donors.");
			}
			if(hasNext){ System.out.println("Choose the number of the donor that is going to donate the organ.");
			
			do {
				Donor d = donorIterator.next();
				System.out.println(counterDon + ". " + d);
				donors.add(d);
				
				counterDon++;
			}while(donorIterator.hasNext());
			int numDon=Integer.parseInt(console.readLine());
			Donor don= donors.get(numDon - 1);
			
			int idO = dbManager.orgIdByDonIdAndReqOrg(don.getId(), reqOrgan.getName());
		
			dbManager.requestedFKinOrgan(reqOrgan.getId(), idO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
