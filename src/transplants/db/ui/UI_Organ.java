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
		
	public void introduceNewOrgan(Donor donor, DBManager dbManager, JPAmanager jpaManager) {
		try {
			boolean more = true;// one patient can request many organs so...
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
				//inserting the new organ w/JDBC
				boolean ok = dbManager.insert(organ);
				// get the id of the donor with JPA
				int idDonor = jpaManager.idDonor(donor);				
				organ= jpaManager.organOfADonor(idDonor);
				boolean okFKDonor= donor.addOrgan(organ);

				if (ok && okFKDonor) {
					System.out.println("Organ has been introduced");
					//uiCompatibilityTest(organ, dbManager);
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

	public void uiCompatibilityTest(Organ organ, DBManager dbManager) {
		List<Patient> compatiblePatients = new ArrayList<Patient>();

		try {
			compatiblePatients = dbManager.dbCompatibilityTest(organ);
			Iterator<Patient> it = compatiblePatients.iterator();
			int counterPat = 1;
			while (it.hasNext()) {
				Patient p = it.next();
				System.out.println(counterPat + ". " + p);
				counterPat++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Organ> searchOrgan(DBManager dbManager) {
		try {
			System.out.println("Introduce the name of the organ: ");
			String name = console.readLine();
			List<Organ> organ = dbManager.searchOrgan(name);
			return organ;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void updateOrgan(Organ organ, DBManager dbManager) {
		boolean again = true;
		try {
			while (again) {
				System.out.println("Choose the information that is going to be updated [1-7]: ");
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
			} else {
				System.out.println("Organ has NOT been updated. ");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteOrgan(Organ organ, DBManager dbManager) {
		try {
			boolean deleted = dbManager.delete(organ);
			if (deleted) {
				System.out.println("Organ has been deleted.");
			} else {
				System.out.println("Organ has NOT been deleted. ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void organsOfDonor(Donor d, DBManager dbManager) {
		try {
			int idDon = d.getId();
			List<Organ> organs = dbManager.organsOfDonor(idDon);
			System.out.println("Donor: " + d.getName() + " donates the following organs: \n");
			Iterator<Organ> itOrg = organs.iterator();
			int countOrg = 1;
			while (itOrg.hasNext()) {
				Organ o = itOrg.next();
				System.out.println(countOrg + ". " + o.getName());
				countOrg++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Organ> organsThatMatchRequestByName(String reqName, DBManager dbManager) {
		List<Organ> matchByNameOrgs = new ArrayList<Organ>();
		try {
			matchByNameOrgs = dbManager.searchOrgan(reqName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return matchByNameOrgs;
	}

	public boolean insertRequestedFKinOrgan(int idReq, int idOrg, DBManager dbManager) {
		try {
			return dbManager.requestedFKinOrgan(idReq, idOrg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Donor getDonorOfOrgan(Organ org, JPAmanager jpaManager) {
		Donor don = new Donor();
		try {
			don = jpaManager.getDonorOfOrg(org.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return don;
	}
}
