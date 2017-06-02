package transplants.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import transplants.db.pojos.Patient;

public class JPApatient {
	
	//ATTRIBUTES
	private JPAmanager jpaManager;

	
	//METHODS
	public JPApatient(JPAmanager jpamanager) {
		this.jpaManager = jpamanager;

	}
	//This is used in order to insert a patient
	public boolean insert(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().persist(patient);
			jpaManager.getEManager().getTransaction().commit();			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//This is used in order to remove a patient
	public boolean removePatient(Patient patient) {

		try {
			jpaManager.getEManager().getTransaction().begin();
			jpaManager.getEManager().remove(patient);
			jpaManager.getEManager().getTransaction().commit();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//This method is used to get the id of a specific patient that we are passing as a parameter
	public Integer getIdpatient(Patient pat){
		
		Patient patient=new Patient();
		try {
			Query q1 = jpaManager.getEManager().createNativeQuery(
			"SELECT id FROM Patients WHERE name LIKE ? AND birthDate = ? AND weight = ? AND height = ? AND gender"
		    + " LIKE ? AND pathology LIKE ? AND bloodType LIKE ? AND additionDate = ?"
			+ " AND lifeExpectancy = ? ", Patient.class);
			
			q1.setParameter(1, pat.getName());
			q1.setParameter(2, pat.getBirthDate());
			q1.setParameter(3, pat.getWeight());
			q1.setParameter(4, pat.getHeight());
			q1.setParameter(5, pat.getGender());
			q1.setParameter(6, pat.getPathology());
			q1.setParameter(7, pat.getBloodType());
			q1.setParameter(8, pat.getAdditionDate());
			q1.setParameter(9, pat.getLifeExpectancy());
			
			patient=(Patient) q1.getSingleResult();
			return patient.getId();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return 30;
	}

	
	//This method is used to search a patient by its name
	public List<Patient> searchPatient(String name) {
		List<Patient> patients = new ArrayList<Patient>();
		try {			
			Query q = jpaManager.getEManager().
					createNativeQuery("SELECT * FROM Patients WHERE name LIKE '%" + name + "%'", Patient.class);
			patients = (List<Patient>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	//This method is used to search a specific patient, that is why we are using the id 
	//as a searching parameter
	public Patient searchPatientById (Integer idP){
		Patient p = new Patient();
		try{
			Query q = jpaManager.getEManager().createNativeQuery("SELECT * FROM Patients WHERE id = ? ", Patient.class);
			q.setParameter(1, idP);
			p = (Patient) q.getSingleResult();
		}catch (Exception e){
			e.printStackTrace();
		}
		return p;
	}

}
