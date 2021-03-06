package transplants.db.ui;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.jpa.JPAmanager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;
import transplants.db.pojos.Patient;
import transplants.db.pojos.TransplantDatabase;
import transplants.db.xml.XMLmanager;

public class UI_Hospitals {

	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public UI_Hospitals() {
	}

	//Insertion of a hospital
	public void introduceNewHospital(DBManager dbManager, JPAmanager jpaManager, TransplantDatabase data) {
		try {
			System.out.print("Name: ");
			String name = console.readLine();
			//Phone number and post code are Strings to give the user more freedom
			System.out.print("Phone number: ");
			String phone_number = console.readLine();

			System.out.print("Address: ");
			String address = console.readLine();

			System.out.print("City: ");
			String city = console.readLine();

			System.out.print("Postcode: ");
			String post_code = console.readLine();

			System.out.print("Country: ");
			String country = console.readLine();

			Hospital hosp = new Hospital(name, phone_number, address, city, post_code, country);
			
			//The insert of a hospital is going to be done with jpa
			boolean ok = jpaManager.insert(hosp);
			//We use the class TransplantDatabase just to store the hospitals
			//Is not a real relation so we do the following
			boolean okDatabase = data.addHospital(hosp);
			if (ok && okDatabase) {
				System.out.print("Hospital has been introduced");
			} else {
				System.out.print("Hospital has NOT been introduced");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Get a hospital by its name
	public List<Hospital> searchHospital(DBManager dbManager) {
		try {
			System.out.println("Introduce the name of the hospital: ");
			String name = console.readLine();
			List<Hospital> hospitals = dbManager.searchHosp(name);
			return hospitals;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//Update the information of a hospital
	public void updateHospital(Hospital hosp, DBManager dbManager) {
		boolean again = true;
		try {
			while (again) {
				System.out.println("1. Name");
				System.out.println("2. Phone number");
				System.out.println("3. Address");
				System.out.println("4. City");
				System.out.println("5. Postcode");
				System.out.println("6. Country");
				System.out.println("Choose the information that is going to be updated [1-6]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op) {
				case 1:
					System.out.println("Introduce the new name: ");
					hosp.setName(console.readLine());
					break;
				case 2:
					System.out.println("Introduce the new phone number: ");
					hosp.setPhone_number(console.readLine());
					break;
				case 3:
					System.out.println("Introduce the new address: ");
					hosp.setAddress(console.readLine());
					break;
				case 4:
					System.out.println("Introduce the new city: ");
					hosp.setCity(console.readLine());
					break;
				case 5:
					System.out.println("Introduce the new postcode: ");
					hosp.setPostcode(console.readLine());
					break;
				case 6:
					System.out.println("Introduce the new country: ");
					hosp.setCountry(console.readLine());
					break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if ((console.readLine()).equals("no")) {
					again = false;
				}
			}

			boolean updated = dbManager.update(hosp);
			if (updated) {
				System.out.println("Hospital has been updated. \n" + hosp.toString());
			} else {
				System.out.println("Hospital has not been updated. ");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	//Delete a hospital
	public void deleteHospital(Hospital hosp, DBManager dbManager) {
		try {
			boolean deleted = dbManager.delete(hosp);
			if (deleted) {
				System.out.println("Hospital has been deleted.");
			} else {
				System.out.println("Hospital has not been deleted. ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//This method is used to see in which hospital(s) works a given doctor
	public void DoctorHospital(String docName, DBManager dbManager) {
		try {
			List<Hospital> hospitals = dbManager.hospitalsOfDoctor(docName);
			System.out.println("The doctor: " + docName + " works in the following hospitals:\n ");
			Iterator<Hospital> it = hospitals.iterator();
			int count = 1;
			while (it.hasNext()) {
				System.out.println(count + ". " + it.next().getName());
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//This method shows all the patients that are admitted in a hospital
	public void seeallpatients(Hospital hosp, JPAmanager jpaM) {		
		try {
			List<Patient> listpatients = new ArrayList<Patient>();
			System.out.println("The patients that this hospital has: ");
			listpatients = jpaM.searchAllPatients(hosp);
			Iterator<Patient> it = listpatients.iterator();
			int count = 1;
			while (it.hasNext()) {
				System.out.println(count + ". " + it.next());
				count++;
			}
			if (listpatients.isEmpty()){
				System.out.println("This hospital doesn't have any patients yet. ");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
	}

	//Method that stores some information of the database into a XML file
	public void javaToXmlDatabase(DBManager dbManager, JPAmanager jpaManager, TransplantDatabase data) {
		try {
			// Get all the hospitals to marshal
			List<Hospital> hospsToMarshall = data.getAllHospOFDatabase();
			Iterator<Hospital> itH = hospsToMarshall.iterator();
			//Get the doctors and patients of the hospital and add them to the hospital
			int counterH = 1;
			while (itH.hasNext()) {
				Hospital h = itH.next();
				// print all the hospitals in order to see if all of them are being marshalled
				System.out.println(counterH + ". Hospital: " + h.getName());
				List<Doctor> doctorsOfHosp = dbManager.workingDoctorsInHosp(h.getName());
				Iterator<Doctor> itD = doctorsOfHosp.iterator();
				while (itD.hasNext()) {
					Doctor d = itD.next();
					boolean doctorOK = h.addDoctor(d);
					System.out.println("Doctor " + d.getNameOfDoctor() + " added: " + doctorOK); // checking
																									// addition
				}
				List<Patient> patientsOfHosp = jpaManager.searchAllPatients(h);
				Iterator<Patient> itP = patientsOfHosp.iterator();
				while (itP.hasNext()) {
					Patient p = itP.next();
					boolean patientOK = h.addPatient(p);
					System.out.println("Patient " + p.getName() + " added:" + patientOK); // checking
																							// addition
				}
				counterH++;
			}

			XMLmanager hospXml = new XMLmanager();
			System.out.println("Starting marshal.");
			boolean xmlOK = hospXml.marshalDatabase(data);

			if (xmlOK) {
				System.out.println("Xml of hospital created. ");
			} else {
				System.out.println("Xml of hospital NOT created. ");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Method that gets the information from the XML file
	public void xmlToJavaDatabase(DBManager dbManager, JPAmanager jpaManager, TransplantDatabase dataUnmarsh) {
		try {
			XMLmanager dataXml = new XMLmanager();
			dataUnmarsh = dataXml.unmarshalDatabase(dataUnmarsh);
			//All the information is contained in a transplantDatabase object
			//So we get all the hospitals from it, and then the doctors and patients from each hospital
			//And then update the information from the XML to the database
			List<Hospital> hospUnmarsh = dataUnmarsh.getAllHospOFDatabase();
			for (Hospital h : hospUnmarsh) {
				List<Doctor> docsUnmarsh = h.getDoctors();
				for (Doctor d : docsUnmarsh) {
					boolean docUpdate = dbManager.update(d);
					if (docUpdate) {
						System.out.println("Doctor " + d.getNameOfDoctor() + " updated.");
					}
				}
				List<Patient> patsUnmarsh = h.getPatients();
				for (Patient p : patsUnmarsh) {
					Patient temp = jpaManager.getPatientById(p.getId());		
					temp.setName(p.getName());
					temp.setBirthDate(p.getBirthDate());
					temp.setWeight(p.getWeight());
					temp.setHeight(p.getHeight());
					temp.setGender(p.getGender());
					temp.setBloodType(p.getBloodType());
					temp.setLifeExpectancy(p.getLifeExpectancy());
					temp.setPathology(p.getPathology());
					temp.setAdditionDate(p.getAdditionDate());
					temp.setScore(p.getScore());

					temp.setHospital(h);
					h.addPatient(temp);
					jpaManager.update(temp);
					jpaManager.update(h);
					System.out.println("Patient " + temp.getName() + " updated.");
				}
				boolean hospUpdate = dbManager.update(h);
				if (hospUpdate) {
					System.out.println("Hospital " + h.getName() + " updated.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	//Method from which the HTML is obtained
	public void xmlToHtml(String sourcePath, String xsltPath, String resultDir) {
		XMLmanager hospXml = new XMLmanager();
		hospXml.simpleTransform(sourcePath, xsltPath, resultDir);
	}
}
