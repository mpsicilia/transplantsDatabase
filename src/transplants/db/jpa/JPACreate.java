package transplants.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import transJPA.db.pojos.Hospital;
import transJPA.db.ui.Uihospital;


public class JPACreate implements JPAinterface{
private JPAhospital hosp;
	
	@Override
	public void create(Object obj) {
		try{
			
			
			if (Hospital.class==obj.getClass()){
				//create connection
				hosp = new JPAhospital(); 
				Hospital hospital=(Hospital)obj;			
				return hosp.create(hospital);
			}
			/*
			if (Doctor.class==obj.getClass()){
				doct = new SQL_Doctor (this);
				Doctor doctor=(Doctor)obj;			
				return doct.insertDoctor(doctor);
			}
			
			if(Patient.class==obj.getClass()){
				pat = new SQL_Patient (this);
				Patient patient=(Patient)obj;
				return pat.insertPatient(patient);
			}			
			if (Donor.class==obj.getClass()){
				don = new SQL_Donor (this); 
				Donor donor=(Donor)obj;
				return don.insertDonor(donor);
			}
			if (Organ.class==obj.getClass()){
				org = new SQL_Organ (this);
				Organ organ=(Organ)obj;
				return org.insertOrgan(organ);
			}
			if (Animal_tissue.class==obj.getClass()){
				animalT= new SQL_AnimalTissue(this);
				Animal_tissue animalTi=(Animal_tissue)obj;
				return animalT.insertAnimalTissue(animalTi);
			}
			if (Requested_organ.class==obj.getClass()){
				req= new SQL_Request(this);
				Requested_organ reqOrgan=(Requested_organ)obj;
				return req.insertRequest(reqOrgan);
			}*/
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
		
	}
	
	/*public static void main(String[] args) throws IOException {
		// Get the entity manager
		EntityManager em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		// Get the department info from the command prompt
		System.out.println("Please, input the department info:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Name: ");
		String name = reader.readLine();
		System.out.print("Address: ");
		String address = reader.readLine();
					
		// Create the object
		Department dep1 = new Department(name, address);
		
		// Begin transaction 
		em.getTransaction().begin();
		// Store the object
		em.persist(dep1);
		// End transaction
		em.getTransaction().commit();
		
		// Close the entity manager
		em.close();}*/
	}

