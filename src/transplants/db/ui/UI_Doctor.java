package transplants.db.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import transplants.db.jdbc.DBManager;
import transplants.db.pojos.Doctor;
import transplants.db.pojos.Hospital;

public class UI_Doctor {

	BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

	public UI_Doctor() {
	}

	// This class is going to work with JDBC
	public void introduceNewDoctor(DBManager dbManager) {
		try {
			System.out.print("Name of the doctor: ");
			String name = console.readLine();

			System.out.print("Registration number: ");
			String regNumber = console.readLine();

			System.out.print("Specialization: ");
			String specializ = console.readLine();

			Doctor doct = new Doctor(name, regNumber, specializ);
			boolean ok = dbManager.insert(doct);

			// get the id of the doctor, to use it when introducing FK
			Integer id = dbManager.getIdOfDoctor(doct);
			System.out.println("In how many hospitals is going to work the doctor?");
			int times = Integer.parseInt(console.readLine());
			int count = 0;
			boolean ok2 = false;
			// List<Hospital> listHospital = doct.getHospital();

			do {
				// first we show to the user all the hospitals
				List<Hospital> listHosp = dbManager.selectAllHospitals();
				Iterator<Hospital> itH = listHosp.iterator();
				while (itH.hasNext()) {
					Hospital hosp = itH.next();
					System.out.println(hosp);
				}
				System.out.print("Introduce the id of the hospital in which the doctor works: ");
				Integer idHospYouChoose = Integer.parseInt(console.readLine());
				ok2 = dbManager.insertPrimaryKeyDoctorHospital(idHospYouChoose, id);

				count++;

				// TENIENDO EL ID DEL HOSPITAL, BUSCO SU NOMBRE Y UNA VEZ LO
				// TENGO BUSCO
				// HOSPITAL CON ESE NOMBRE Y LO GUARDO EN LA LISTA
				// DE HOSPITALES DEL DOCTOR.
				// String namehospital =
				// dbManager.searchHospital(idHospYouChoose);
				// listHospital = dbManager.searchHosp(namehospital);
				// doct.setHospital(listHospital);

			} while (count < times);
				
			if (ok && ok2) {
				System.out.print("The doctor has been introduced correctly");
			} else {
				System.out.print("The doctor has NOT been introduced");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<Doctor> searchDoctor(DBManager dbManager) {
		try {
			System.out.println("Introduce the name of the doctor: ");
			String name = console.readLine();
			List<Doctor> doctor = dbManager.searchDoctor(name);
			return doctor;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public void updateDoctor(Doctor doct, DBManager dbManager) {
		boolean again = true;
		try {
			while (again) {
				System.out.println("1. Name of the doctor");
				System.out.println("2. Registration number");
				System.out.println("3. Specialization");
				System.out.println("Choose the information that is going to be updated [1-3]: ");
				int op = Integer.parseInt(console.readLine());
				switch (op) {
				case 1:
					System.out.println("Introduce the new name: ");
					doct.setNameOfDoctor(console.readLine());
					break;
				case 2:
					System.out.println("Introduce the new registration number: ");
					doct.setRegistrationNumber(console.readLine());
					break;
				case 3:
					System.out.println("Introduce the new specialization: ");
					doct.setSpecialization(console.readLine());
					break;
				}
				System.out.println("Do you want to update more information? [yes/no]");
				if ((console.readLine()).equals("no")) {
					again = false;
				}
			}

			boolean updated = dbManager.update(doct);
			if (updated) {
				System.out.println("Doctor information has been updated. \n" + doct.toString());
			} else {
				System.out.println("Doctor information has NOT been updated. ");
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void deleteDoctor(Doctor doc, DBManager dbManager) {
		try {
			boolean deleted = dbManager.delete(doc);
			if (deleted) {
				System.out.println("Doctor has been deleted.");
			} else {
				System.out.println("Doctor has not been deleted. ");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
