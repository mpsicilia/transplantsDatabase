package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Donor;
import transplants.db.pojos.Organ;
import transplants.db.pojos.Patient;

public class UI_Organ {
	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public UI_Organ() {

	}
	//Insertion of donated organs
	//One donor can donate many organs so we give the option to introduce more organs
	public void introduceNewOrgan(Donor donor, DBManager dbManager, JPAmanager jpaManager) {
		try {
			boolean more = true;
			while (more) {

				System.out.print("Name: ");
				String name = console.readLine();

				System.out.print("Weight(kg): ");
				Float weight = Float.parseFloat(console.readLine());
				
				System.out.print("Type of donation [total or partial]: ");
				String typeOfDonation = console.readLine();
				
				System.out.print("Life of organ [yyyy-mm-dd]: ");
				String lifeOfOrg= console.readLine();
				Date lifeOfOrgan= Date.valueOf(lifeOfOrg);	

				Organ organ = new Organ(name, weight, typeOfDonation, lifeOfOrgan);
				
				boolean ok = jpaManager.insert(organ);
				
				//Link both sides
				donor.addOrgan(organ);
				organ.setDonor(donor);
				// And update both sides
				boolean okUpdateOrgan = jpaManager.update(organ);
				boolean okUpdateDonor = jpaManager.update(donor);

				if (ok && okUpdateOrgan && okUpdateDonor) {
					System.out.println("Organ has been introduced\n");
					dbManager.expired();
					uiCompatibilityTest(organ, dbManager);
				} else {
					System.out.println("Organ has NOT been introduced");
				}
				System.out.println("Is the donor going to donate another organ? [yes/no]");
				String another = console.readLine();
				if (another.equalsIgnoreCase("no")) {
					more = false;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//Update the information of an organ
	public void updateOrgan(Organ organ, DBManager dbManager) {
		boolean again = true;
		try {
			while (again) {
				System.out.println("Choose the information that is going to be updated [1-4]: ");
				System.out.println("1. Name");
				System.out.println("2. Weight");				
				System.out.println("3. Type of donation");
				System.out.println("4. Life of organ");
				int op = Integer.parseInt(console.readLine());
				switch (op) {
				case 1:
					System.out.println("Introduce the new name: ");
					organ.setName(console.readLine());
					
					break;
				case 2:
					System.out.println("Introduce the new weight: ");
					organ.setWeight(Float.parseFloat(console.readLine()));
					break;				
				case 3:
					System.out.println("Introduce the new type of donation: ");
					organ.setTypeOfDonation(console.readLine());
					break;
				case 4:
					System.out.println("Introduce the new life of organ");
					organ.setLifeOfOrgan(Date.valueOf(console.readLine()));
					break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if ((console.readLine()).equals("no")) {
					again = false;
				}
			}

			boolean updated = dbManager.update(organ);
			if (updated) {
				System.out.println("Organ has been updated. \n" + organ.toString());
				//When the organ is updated, we check if it's no longer valid in order to delete it
				//or if it's now compatible with any requested organ
				dbManager.expired();
				uiCompatibilityTest(organ, dbManager);
			} else {
				System.out.println("Organ has NOT been updated. ");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Deletion of an organ
	public void deleteOrgan(Donor donor, Organ organ, JPAmanager jpaManager) {
		try {
			donor.removeOrgan(organ);
			organ.setDonor(donor);
			boolean okUpdateOrgan = jpaManager.update(organ);
			boolean okUpdateDonor = jpaManager.update(donor);
			
			boolean deleted = jpaManager.delete(organ);
			if (deleted && okUpdateOrgan && okUpdateDonor) {
				System.out.println("Organ has been deleted.");
			} else {
				System.out.println("Organ has NOT been deleted. ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	//Get the organs that a donor donates
	public List<Organ> organsOfDonor(Donor d, JPAmanager jpaManager) {
		try {
			List<Organ> organs = d.getOrgans();
			Integer counterOrg= 1;
			if (!organs.isEmpty()){
				System.out.print("\nThe organs that this donor is donating are:\n");
				for (Organ organ : organs) {
					System.out.println(counterOrg + ". " + organ);
					counterOrg++;
				}
				return organs;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Method that given a donated organ checks all the requested organs from the database
	//Looking for compatibility with the requested organs and its patients
	public void uiCompatibilityTest(Organ organ, DBManager dbManager) {
		List<Patient> compatiblePatients = new ArrayList<Patient>();
		List<Patient> patients= new ArrayList<Patient>();
		try {
			compatiblePatients = dbManager.dbCompatibilityTest(organ);
			Iterator<Patient> patientIterator = compatiblePatients.iterator();
			
			int counterPat = 1;
			Boolean hasNext= patientIterator.hasNext();
			if(!hasNext){
				System.out.println("At this moment there are no compatible patients.");
			}
			if(hasNext){ System.out.println("Choose the number of the patient that is going to recieve the organ");
			
			do {
				Patient p = patientIterator.next();
				System.out.println(counterPat + ". " + p);
				patients.add(p);
				
				counterPat++;
			}while(patientIterator.hasNext());
			int numPat=Integer.parseInt(console.readLine());
			Patient pat= patients.get(numPat - 1);
			
			int idR = dbManager.reqIdByPatIdAndDonOrg(pat.getId(), organ.getName());
		
			dbManager.requestedFKinOrgan(idR, organ.getId());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
